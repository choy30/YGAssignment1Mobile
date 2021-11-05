package com.example.mobiletest3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.afollestad.sectionedrecyclerview.SectionedViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeAdapter extends SectionedRecyclerViewAdapter<TimeAdapter.ViewHolder> {

    Activity activity;
    ArrayList<String> sectionList;
    HashMap<String, ArrayList<String>> itemList = new HashMap<>();
    int selectedSection = -1;
    int selectedItem = -1;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "srn";
    private static final String KEY_TIME = "time";



    public TimeAdapter(Activity activity, ArrayList<String> sectionList, HashMap<String, ArrayList<String>> itemList) {
        this.activity = activity;
        this.sectionList = sectionList;
        this.itemList = itemList;
        notifyDataSetChanged();

    }

    @Override
    public int getSectionCount() {
        return sectionList.size();
    }

    @Override
    public int getItemCount(int section) {
        return itemList.get(sectionList.get(section)).size();
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder, int section, boolean expanded) {
        holder.textView.setText(sectionList.get(section));

    }

    @Override
    public void onBindFooterViewHolder(ViewHolder holder, int section) {

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int section, int relativePosition, int absolutePosition) {
        sharedPreferences = activity.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String sItem = itemList.get(sectionList.get(section)).get(relativePosition);
        holder.textView.setText(sItem);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_TIME, sItem);
                editor.apply();

                Toast.makeText(activity,sItem,Toast.LENGTH_SHORT).show();
                selectedSection = section;
                selectedItem = relativePosition;
                notifyDataSetChanged();
            }
        });
        if(selectedSection == section && selectedItem == relativePosition) {
            holder.textView.setBackground(ContextCompat.getDrawable(activity,R.drawable.boxfillshape));
            holder.textView.setTextColor(Color.WHITE);
        }else {
            holder.textView.setBackground(ContextCompat.getDrawable(activity,R.drawable.boxshape));
            holder.textView.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        if(section == 1) {
            return 0;
        }
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout;
        if(viewType == VIEW_TYPE_HEADER) {
            layout = R.layout.item_slot;
        } else {
            layout = R.layout.item_slot;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends SectionedViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }

}
