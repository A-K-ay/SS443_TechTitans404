package com.example.eveutopia;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

//    FirebaseUser user;
    public static final String CHANNEL_1_ID = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();

//        user = FirebaseAuth.getInstance().getCurrentUser();
//        checkIfLoggedIn();

        createNotificatonChannels();
    }

//    private void checkIfLoggedIn() {
//        if (user == null) {
//            startActivity(new Intent(getBaseContext(),MainActivity.class));
//        }
//        else{
//            startActivity(new Intent(getBaseContext(),homeActivity.class));
//        }
//
//    }

    private void createNotificatonChannels() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "EV Battery Alert Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Alerts the user when the battery level of the vehicle falls below 30%");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }
    }
}
