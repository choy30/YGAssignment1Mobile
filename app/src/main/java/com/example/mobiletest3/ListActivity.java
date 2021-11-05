package com.example.mobiletest3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    TextView txt_license, txt_email;
    RecyclerView recyclerView;
    Button btn_logout;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "srn";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_LICENSE = "license";


    String s1[], s2[], s3[];
    int images[] = {R.drawable.glen_innes, R.drawable.highbrook, R.drawable.manukau_city, R.drawable.new_lynn, R.drawable.north_shore, R.drawable.takanini};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        txt_license = findViewById(R.id.txt_license);
        txt_email = findViewById(R.id.txt_email);
        btn_logout = findViewById(R.id.btn_logout);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String license = sharedPreferences.getString(KEY_LICENSE, null);
        String email = sharedPreferences.getString(KEY_EMAIL,null);

        if(license != null || email != null) {
            txt_license.setText("License Number"+license);
            txt_email.setText(email);
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
            }
        });


        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.address1);
        s2 = getResources().getStringArray(R.array.address2);
        s3 = getResources().getStringArray(R.array.contactNumber);

        NztaAdapter nztaAdapter = new NztaAdapter(this, s1, s2, s3, images);
        recyclerView.setAdapter(nztaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}