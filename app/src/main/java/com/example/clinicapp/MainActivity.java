package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.example.clinicapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    LowBatteryReceiver lowBatteryReceiver;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FILTRU PENTRU BATERIE SLABA
        lowBatteryReceiver = new LowBatteryReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        this.registerReceiver(lowBatteryReceiver, filter);



        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.investigations) {
                replaceFragment(new InvestigationsFragment());
            } else if (item.getItemId() == R.id.account) {
                replaceFragment(new AccountFragment());
            }
            return true;
        });

//        DATABASE HELPER PENTRU BAZA DE DATE (nu il folosesc aici, a fost de test
//        SQLiteDatabase db = new DatabaseHelper(this).getReadableDatabase();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        ANIMATIE PENTRU FRAGMENTE
        fragmentTransaction.setCustomAnimations(R.anim.from_left, R.anim.to_right);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(lowBatteryReceiver);
    }
}