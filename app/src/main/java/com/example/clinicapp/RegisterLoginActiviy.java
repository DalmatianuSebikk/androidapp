package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterLoginActiviy extends AppCompatActivity {

    EditText editTextName, editTextEmail;
    Button registerButton;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login_activiy);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        registerButton = findViewById(R.id.registerButton);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);

        if (name != null) {
            // chemi direct activitatea normala
            Intent intent = new Intent(RegisterLoginActiviy.this, MainActivity.class);
            startActivity(intent);
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, editTextName.getText().toString());
                editor.putString(KEY_EMAIL, editTextEmail.getText().toString());
                editor.apply();

                Intent intent = new Intent(RegisterLoginActiviy.this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(RegisterLoginActiviy.this, "Login succes", Toast.LENGTH_SHORT).show();
            }
        });
    }
}