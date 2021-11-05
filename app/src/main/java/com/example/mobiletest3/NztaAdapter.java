package com.example.mobiletest3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class NztaAdapter extends RecyclerView.Adapter<NztaAdapter.NztaViewHolder> {

    String data1[], data2[], data3[];
    int images[];
    Context context;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "srn";
    private static final String KEY_ADDRESS = "address";

    public NztaAdapter(Context ct,String s1[], String s2[], String s3[], int img[]) {
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        images = img;

    }

    @NonNull
    @Override
    public NztaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.nzta_row, parent, false);
        return new NztaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NztaViewHolder holder, @SuppressLint("RecyclerView") int position) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        holder.address1.setText(data1[position]);
        holder.address2.setText(data2[position]);
        holder.contactNumber.setText(data3[position]);
        holder.nztaImage.setImageResource(images[position]);



        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_ADDRESS, holder.address1.getText().toString());
                editor.apply();


                Intent intent = new Intent(context, BookingActivity.class);
                intent.putExtra("nztaImage", images[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class NztaViewHolder extends RecyclerView.ViewHolder{

        TextView address1, address2, contactNumber;
        ImageView nztaImage;
        ConstraintLayout mainLayout;

        public NztaViewHolder(@NonNull View itemView) {
            super(itemView);
            address1 = itemView.findViewById(R.id.address1);
            address2 = itemView.findViewById(R.id.address2);
            contactNumber = itemView.findViewById(R.id.contactNumber);
            nztaImage = itemView.findViewById(R.id.nztaImage);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
