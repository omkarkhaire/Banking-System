package com.example.bankingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.bankingsystem.allcustomers.imagecollection;

public class transactionadapter extends RecyclerView.Adapter<transactionadapter.ViewHolder> {
    private Context context;
    private ArrayList<customermodel> alltransaction;

    public transactionadapter(Context context, ArrayList<customermodel> alltransaction) {
        this.context = context;
        this.alltransaction = alltransaction;
    }

    @NonNull
    @Override
    public transactionadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item2,parent,false);
        transactionadapter.ViewHolder viewHolder=new transactionadapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull transactionadapter.ViewHolder holder, int position) {
        holder.from.setText(alltransaction.get(position).getName().toString());
        holder.to.setText(alltransaction.get(position).getEmailid().toString());
        holder.amount.setText("Rs "+alltransaction.get(position).getBalance()+"/-");


    }

    @Override
    public int getItemCount() {
        return alltransaction.size();
    }
    public class ViewHolder  extends RecyclerView.ViewHolder{
        //        CardView largecard;

        TextView from;
        TextView to;
        TextView amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//           largecard=itemView.findViewById(R.id.cardbig);
            from=itemView.findViewById(R.id.from);
            to=itemView.findViewById(R.id.to);
            amount=itemView.findViewById(R.id.amount);

        }

    }
}
