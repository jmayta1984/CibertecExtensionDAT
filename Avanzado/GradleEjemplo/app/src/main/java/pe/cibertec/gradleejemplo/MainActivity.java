package pe.cibertec.gradleejemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.FLAVOR.equals("demo")){
            Toast.makeText(this, "Versión demo", Toast.LENGTH_SHORT).show();
        }

        if (BuildConfig.FLAVOR.equals("full")){
            Toast.makeText(this, "Versión full", Toast.LENGTH_SHORT).show();
        }
    }
}
