package pe.edu.cibertec.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btCamera;
    ImageView ivPhoto;

    // Indica el código de quien hizo el pedido
    static final int REQUEST_CAMERA = 1;

    // Indica la ruta de la imagen
    String currentPathImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCamera = findViewById(R.id.btCamera);
        ivPhoto = findViewById(R.id.ivPhoto);

        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });


    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Valida que este disponible la cámara
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {


            // Crea el archivo para la imagen

            File photoFile = null;

            try {
                photoFile = createImage();
            } catch (IOException ex) {
                // En caso se origine un error al crear el archivo para la imagen
                Log.d("MainActivity", ex.toString());
            }

            // Valida si el archivo se ha creado correctamente
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this,
                        "pe.edu.cibertec.camera",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_CAMERA);


            }


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    /*
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK ) {
            Bundle extras = data.getExtras();

            Bitmap image = (Bitmap) extras.get("data");

            ivPhoto.setImageBitmap(image);
        }
    */

    if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK){
        Glide.with(this).load(currentPathImage).into(ivPhoto);

    }


    }

    private File createImage() throws IOException {
        // Crea el nombre del archivo de la imagen

        String timeStamp = new SimpleDateFormat
                ("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName, // nombre
                ".jpg",   // extensión
                storageDir);   // directorio

        // Graba la imagen

        currentPathImage = image.getAbsolutePath();
        return image;


    }
}
