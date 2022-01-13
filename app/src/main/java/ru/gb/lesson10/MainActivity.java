package ru.gb.lesson10;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements CreateNameController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.show_toast).setOnClickListener(
                v -> Toast.makeText(
                        MainActivity.this.getApplicationContext(),
                        "This is a toast!",
                        Toast.LENGTH_LONG).show()
        );

        findViewById(R.id.show_snackbar).setOnClickListener(v -> {
            Snackbar bar = Snackbar.make(v, "This is a snackbar", Snackbar.LENGTH_SHORT);
            bar.setAction("Dismiss", view -> bar.dismiss());
            bar.show();
        }
        );

        findViewById(R.id.show_alert).setOnClickListener(v -> showAlertDialog());

        findViewById(R.id.show_custom_alert).setOnClickListener(v -> showCustomAlertDialog());


        findViewById(R.id.show_custom_dialog_fragment).setOnClickListener(v -> showCustomDialogFragment());

        findViewById(R.id.show_bottom_sheet).setOnClickListener( v -> showBottomDialogFragment());


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            createNotificationChannel();
        }


        findViewById(R.id.show_notification).setOnClickListener(v-> showNotification());


    }

    public static final String CHANNEL_ID = "BULK_MESSAGES";

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        String channelName = "Bulk";
        String channelDescription = "Bulk Channel Description";
        int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, channelImportance);
        channel.setDescription(channelDescription);
        NotificationManagerCompat.from(this).createNotificationChannel(channel);
    }

    public static final int PENDING_REQUEST_ID = 555;

    private void showNotification() {

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                PENDING_REQUEST_ID,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT
        );


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        builder
                .setContentTitle("Hello")
                .setSmallIcon(android.R.drawable.btn_star)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentText("How about a coffee tonight?");

        NotificationManagerCompat.from(this).notify(42, builder.build());
    }

    private void showBottomDialogFragment() {
        new CreateNameBottomFragment().show(getSupportFragmentManager(), null);
    }

    private void showCustomDialogFragment() {
        new CreateNameDialogFragment().show(getSupportFragmentManager(), null);
    }

    private void showCustomAlertDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.name_dialog, null);
        EditText dialogName = dialogView.findViewById(R.id.dialog_name);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter a name");
        // builder.setMessage("Do you have a name ?");
        builder.setView(dialogView);
        builder.setCancelable(true);

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Answer is no", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = dialogName.getText().toString();
                Toast.makeText(MainActivity.this, "Answer is " + name, Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name");
        builder.setMessage("Do you have a name ?");
        builder.setCancelable(true);

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Answer is no", Toast.LENGTH_SHORT).show();
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Answer is yes", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });



        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void createName(String name) {
        Toast.makeText(this, "Name is " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {


        getSupportFragmentManager().getBackStackEntryCount(); // 1 finish()

        super.onBackPressed();
    }
}