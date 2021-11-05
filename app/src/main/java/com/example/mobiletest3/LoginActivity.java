package com.example.mobiletest3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView textView;
    private Button list;

    EditText text_input_email, text_input_license;
    Button btn_login, btn_booked;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "srn";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_LICENSE = "license";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text_input_email = findViewById(R.id.text_input_email);
        text_input_license = findViewById(R.id.text_input_license);
        btn_login = findViewById(R.id.btn_login);
        btn_booked = findViewById(R.id.btn_booked);

        btn_booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SaveBookingActivity.class);
                startActivity(intent);
            }
        });

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String license = sharedPreferences.getString(KEY_LICENSE, null);

        if(license != null) {
            Intent intent = new Intent(LoginActivity.this, ListActivity.class);
            startActivity(intent);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_LICENSE, text_input_license.getText().toString());
                editor.putString(KEY_EMAIL, text_input_email.getText().toString());
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

    };


}