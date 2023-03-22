package com.example.activitysnacknotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    public String CHANNEL_ID = "Channel_Test";
    public int currentId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CreateNotificationChannel();

        Button mainActionButton = findViewById(R.id.home_main_button);
        mainActionButton.setOnClickListener(v -> VerifyActivities(v));
    }

    private void CreateNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.notification_channel_name);
            String description = getString(R.string.notification_channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void VerifyActivities(View v) {
        int randomNo = (int) Math.ceil((Math.random() * 2) - 1);

        switch (randomNo) {
            case 0:
                ShowNotification();
                break;
            case 1:
                ShowWarning(v);
                break;
        }
    }

    public void ShowNotification() {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID);
        notification.setContentTitle(getResources().getString(R.string.home_notification_title));
        notification.setContentText(getResources().getString(R.string.home_new_activity));
        notification.setSmallIcon(R.drawable.ic_launcher_background);
        notification.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        managerCompat.notify(currentId, notification.build());
        currentId++;
    }

    public void ShowWarning(View v) {
        Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.home_no_activity), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}