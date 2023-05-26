package com.example.clinicapp;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LowBatteryReceiver extends BroadcastReceiver {
//    Inregistrez actiunea in AndroidManifest
//    Iau actiunea cu intent BATTERY LOW
//    in onReceive primesc faptul ca bateria este low si apelez in functie de asta ceva, in cazul acesta o alerta pe ecran.

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
//            faci ceva (afisezi alerta)
            showLowBatteryAlert(context);
        }
    }

    private void showLowBatteryAlert(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Low Battery");
        builder.setMessage("Battery level is low. Please connect your phone to a charger.");
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
