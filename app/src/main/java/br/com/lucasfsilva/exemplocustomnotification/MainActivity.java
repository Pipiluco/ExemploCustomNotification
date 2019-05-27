package br.com.lucasfsilva.exemplocustomnotification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import static br.com.lucasfsilva.exemplocustomnotification.App.CHANNEL_ID;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);
    }


    public void showNotification(View view) {
        RemoteViews normalView = new RemoteViews(getPackageName(), R.layout.notification_normal);
        RemoteViews expandidaView = new RemoteViews(getPackageName(), R.layout.notification_expandida);

        Intent intent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        normalView.setTextViewText(R.id.tvTitulo, "Olá mundo!");
        expandidaView.setImageViewResource(R.id.imgNotificacao, R.drawable.ic_empresa_01);

        expandidaView.setOnClickPendingIntent(R.id.imgNotificacao, pendingIntent);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setCustomContentView(normalView)
                .setCustomBigContentView(expandidaView)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .build();
        notificationManagerCompat.notify(1, notification);
    }

}
