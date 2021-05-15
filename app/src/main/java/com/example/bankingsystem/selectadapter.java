package com.example.bankingsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.bankingsystem.allcustomers.imagecollection;

public class selectadapter extends RecyclerView.Adapter<selectadapter.ViewHolder>{
    private Context context;
    private ArrayList<customermodel> allrecords;
    int prevlocation;
    int transferamount;

    public selectadapter() {


    }

    public selectadapter(@NonNull Context context, ArrayList<customermodel> allrecords, int prevlocation,int transferamount) {
        this.context = context;
        this.allrecords = allrecords;
        this.prevlocation=prevlocation;
        this.transferamount=transferamount;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item,parent,false);
        selectadapter.ViewHolder viewHolder=new selectadapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        if(prevlocation!=position) {


            holder.person.setImageResource(imagecollection.get(position));
            holder.personn.setText(allrecords.get(position).getName().toString());
            holder.personcontact.setText(allrecords.get(position).getMobileno().toString());
            holder.personbalance.setText("Rs " + allrecords.get(position).getBalance());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, persondetail.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("selected", position);
                    intent.putExtra("location", prevlocation);
                    intent.putExtra("amouttotransfer", transferamount);
                    context.startActivity(intent);
                }
            });
        }else {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0)); //to remove space in between
        }

//        holder.itemView.setVisibility(View.VISIBLE); for setting all items visisble if error occurs
//        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    @Override
    public int getItemCount() {
        return allrecords.size()-1;
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
