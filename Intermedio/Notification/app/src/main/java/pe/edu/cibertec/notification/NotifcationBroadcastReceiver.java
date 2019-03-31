package pe.edu.cibertec.notification;


import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import static android.app.Notification.VISIBILITY_PUBLIC;

public class NotifcationBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action == "pe.edu.cibertec.ACTION") {
            //Toast.makeText(context, "Presioné botón", Toast.LENGTH_SHORT).show();


            Intent newIntent = new Intent(context, NotificationActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    newIntent, 0);

            NotificationCompat.Builder notification = new NotificationCompat
                    .Builder(context, "Canal")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Nueva notificación")
                    .setContentText("Esta es una nueva notificación")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)

                    .setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

            managerCompat.notify(0, notification.build());

        } else {
            if (action == "android.intent.action.AIRPLANE_MODE") {
                Toast.makeText(context, "Cambio en modo avión", Toast.LENGTH_SHORT).show();

            }
        }

    }
}
