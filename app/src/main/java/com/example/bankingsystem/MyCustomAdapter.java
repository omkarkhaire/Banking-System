package com.example.bankingsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.bankingsystem.allcustomers.imagecollection;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.ViewHolder> {

    private Context context;
     private ArrayList<customermodel> allrecords;
     private ArrayList<Integer> images;

    public MyCustomAdapter() {


    }

    public MyCustomAdapter(Context context, ArrayList<customermodel> allrecords,ArrayList<Integer> images) {
        this.context=context;
        this.allrecords = allrecords;
        this.images=images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(allrecords.get(position).getName().equals("Kabir") ||allrecords.get(position).getName().equals("Kavin") ||allrecords.get(position).getName().equals("Kiaan"))
        {

            holder.person.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        holder.person.setImageResource(images.get(position));
        holder.personn.setText(allrecords.get(position).getName().toString());
        holder.personcontact.setText(allrecords.get(position).getMobileno().toString());
        holder.personbalance.setText("Rs "+allrecords.get(position).getBalance());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,persondetail.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("location",position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allrecords.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
//        CardView largecard;
        ImageView person;
        TextView personn;
        TextView personcontact;
        TextView personbalance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//           largecard=itemView.findViewById(R.id.cardbig);
            person=itemView.findViewById(R.id.personimg);
            personn=itemView.findViewById(R.id.personname);
            personcontact=itemView.findViewById(R.id.personphone);
            personbalance=itemView.findViewById(R.id.personbalance);

        }

    }
}




























//public class MyCustomAdapter extends ArrayAdapter<customermodel> {
//    private Context context;
//    private ArrayList<customermodel> allrecords;
//    public MyCustomAdapter(@NonNull Context context, ArrayList<customermodel> allrecords) {
//        super(context, R.layout.item,allrecords);
//        this.context = context;
//        this.allrecords = allrecords;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, true);
//        TextView personn=view.findViewById(R.id.personname);
//        TextView personcontact=view.findViewById(R.id.personphone);
//        TextView personbalance=view.findViewById(R.id.personbalance);
//
//        personn.setText(allrecords.get(position).getName().toString());
//        personcontact.setText(allrecords.get(position).getMobileno().toString());
//        personbalance.setText("Rs "+allrecords.get(position).getBalance());
//
//        return view;
//
//
//    }
//
//    @Override
//    public int getCount() {
//        return allrecords.size();
//    }
//
//    @Nullable
//    @Override
//    public customermodel getItem(int position) {
//        return allrecords.get(position);
//    }
//    public long getItemId(int position) {
//        return position;
//    }
//}
