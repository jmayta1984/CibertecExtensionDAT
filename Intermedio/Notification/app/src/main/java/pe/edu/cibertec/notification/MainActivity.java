package pe.edu.cibertec.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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


        registerReceiver();


        btAction = findViewById(R.id.btAction);

        btAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("pe.edu.cibertec.ACTION");
                sendBroadcast(intent);
            }
        });


        createNotificationChannel();

        //showNotification();



    }

    private void showNotification() {

        Intent intent = new Intent(this, NotificationActivity.class);


        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, 0, intent, 0);


        NotificationCompat.Builder notification = new NotificationCompat
                .Builder(this, "Canal")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.text_notification))
                .setContentText(getString(R.string.content_notification))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager
                = NotificationManagerCompat.from(this);

        notificationManager.notify(0, notification.build());

    }

    private void registerReceiver() {
        NotifcationBroadcastReceiver notifcationBroadcastReceiver
                = new NotifcationBroadcastReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction("pe.edu.cibertec.ACTION");


        IntentFilter filterAirplaneMode = new IntentFilter();
        filter.addAction("android.intent.action.AIRPLANE_MODE");

        registerReceiver(notifcationBroadcastReceiver,filter);


        registerReceiver(notifcationBroadcastReceiver,filterAirplaneMode );
    }

    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("Canal",
                    name, importance);

            channel.setDescription(description);

            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);


        }


    }
}

