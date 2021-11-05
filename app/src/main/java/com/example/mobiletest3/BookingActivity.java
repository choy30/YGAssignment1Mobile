package com.example.mobiletest3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class BookingActivity extends AppCompatActivity {

    ArrayList<String> sectionList = new ArrayList<>();
    HashMap<String, ArrayList<String>> itemList = new HashMap<>();
    TimeAdapter adapter;

    RecyclerView recyclerView;
    ImageView nztaImageView;
    int nztaImage;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "srn";
    private static final String KEY_DATE = "date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        nztaImageView = findViewById(R.id.nztaImage);
        getData();
        setData();
        recyclerView = findViewById(R.id.recyclerView);

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, 1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 8);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)

                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String license = sharedPreferences.getString(KEY_DATE, null);

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_DATE, date.getTime().toString());
                editor.apply();
            }
        });

        Button button = findViewById(R.id.btn_save_booking);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingActivity.this, SaveBookingActivity.class));
            }
        });


        recyclerView = findViewById(R.id.time_recycler);

        sectionList.add("Morning");
        sectionList.add("Afternoon");

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("9:00 AM");
        arrayList.add("10:00 AM");
        arrayList.add("11:00 AM");
        itemList.put(sectionList.get(0), arrayList);

        arrayList = new ArrayList<>();

        arrayList.add("12:00 PM");
        arrayList.add("1:00 PM");
        arrayList.add("2:00 PM");
        arrayList.add("3:00 PM");
        arrayList.add("4:00 PM");
        itemList.put(sectionList.get(1), arrayList);

        adapter = new TimeAdapter(this, sectionList,itemList);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
        adapter.setLayoutManager(manager);
        adapter.shouldShowHeadersForEmptySections(false);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void getData() {
        if(getIntent().hasExtra("nztaImage")) {

                nztaImage = getIntent().getIntExtra("nztaImage", 1);

            }
            else {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            }

    }


    private void setData() {
        nztaImageView.setImageResource(nztaImage);
    }

}