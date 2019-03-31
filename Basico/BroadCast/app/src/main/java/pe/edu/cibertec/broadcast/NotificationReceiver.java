package pe.edu.cibertec.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        switch (intent.getAction()) {
            case "pe.edu.cibertec.ACTION":
                String message = "Acción de la aplicación";
                showNotificaction(context, message);
                //Toast.makeText(context, "Acción de la aplicación", Toast.LENGTH_SHORT).show();
                break;
            case "android.intent.action.AIRPLANE_MODE":
                //Toast.makeText(context, "Acción cambio modo avión", Toast.LENGTH_SHORT).show();
                message = "Acción cambio modo avión";
                showNotificaction(context, message);
                break;
        }
    }

    public void showNotificaction(Context context, String messsage) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, "idChannel")
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("Nueva notificación")
                        .setContentText(messsage);


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(0, builder.build());


    }
}
