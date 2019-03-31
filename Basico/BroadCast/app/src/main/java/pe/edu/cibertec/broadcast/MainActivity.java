package pe.edu.cibertec.broadcast;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificacionChannel();
        btAction = findViewById(R.id.btAction);

        btAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Emitir la acción de la aplicación
                Intent intent = new Intent();
                intent.setAction("pe.edu.cibertec.ACTION");
                sendBroadcast(intent);

            }
        });

        // Registrar el Receiver
        registerNotificationReceiver();
    }

    private void createNotificacionChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence channel = "canal";
            String description = "Descripción del canal";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel =
                    new NotificationChannel("idChannel", channel,importance);

            notificationChannel.setDescription(description);

            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);


        }
    }



    private void registerNotificationReceiver() {
        NotificationReceiver receiver = new NotificationReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction("pe.edu.cibertec.ACTION");
        filter.addAction("android.intent.action.AIRPLANE_MODE");
        this.registerReceiver(receiver, filter);

        /*
        // Registrar acción del dispositivo
        IntentFilter airplaneFilter = new IntentFilter();
        airplaneFilter.addAction("android.intent.action.AIRPLANE_MODE");
        this.registerReceiver(receiver, airplaneFilter);
        */

    }
}
