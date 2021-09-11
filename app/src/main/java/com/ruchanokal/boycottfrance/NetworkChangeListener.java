package com.ruchanokal.boycottfrance;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class NetworkChangeListener extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (!Common.isInternetConnected(context)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Connection Problem!");
            alert.setMessage("Please check your internet connection. Network is not avaliable.");
            alert.setCancelable(false);
            alert.setIcon(R.drawable.ic_baseline_wifi_off_24);
            alert.setNeutralButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onReceive(context,intent);
                }
            });
            alert.show();

        }
    }
}
