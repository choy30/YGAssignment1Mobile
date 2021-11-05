package com.example.mobiletest3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SaveBookingActivity extends AppCompatActivity {

    TextView txt_license;
    TextView txt_time;
    TextView txt_address;
    TextView txt_date;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "srn";
    private static final String KEY_LICENSE = "license";
    private static final String KEY_TIME = "time";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_DATE = "date";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_booking);

        txt_license = findViewById(R.id.txt_license);
        txt_time = findViewById(R.id.txt_time);
        txt_address = findViewById(R.id.txt_address);
        txt_date = findViewById(R.id.txt_date);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String license = sharedPreferences.getString(KEY_LICENSE, null);
        String time = sharedPreferences.getString(KEY_TIME, null);
        String address = sharedPreferences.getString(KEY_ADDRESS, null);
        String date = sharedPreferences.getString(KEY_DATE, null);

        txt_license.setText(license);
        txt_time.setText(time);
        txt_address.setText(address);
        txt_date.setText(date);


    }
};