package com.example.bankingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class transactionhistory extends AppCompatActivity {
    RecyclerView list;
    TextView notransaction;
    ArrayList<customermodel> alltranscation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactionhistory);
        list=findViewById(R.id.list);
        notransaction=findViewById(R.id.notransaction);

        getSupportActionBar().setTitle("Transaction History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//home btn
        getSupportActionBar().setDisplayShowHomeEnabled(true);

       databasehelper helpershowtransaction=new databasehelper(getApplicationContext());
       alltranscation=helpershowtransaction.getdatafromtransaction();
       list.setVisibility(View.VISIBLE);
       notransaction.setVisibility(View.GONE);

       if(alltranscation.size()==0)
       {
           list.setVisibility(View.GONE);
           notransaction.setVisibility(View.VISIBLE);
           notransaction.setTextColor(Color.RED);
       }else{
           list.setHasFixedSize(true);
           list.setLayoutManager(new LinearLayoutManager(this));//most important
           transactionadapter adapter=new transactionadapter(getApplicationContext(),alltranscation);
           list.setAdapter(adapter);
       }




    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu2,menu);


        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
//            finish();
            Intent intent=new Intent(getApplicationContext(),allcustomers.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.clear)
        {
            databasehelper helper=new databasehelper(getApplicationContext());
            helper.cleartransaction();
            Toast.makeText(this, "Transactions Cleared", Toast.LENGTH_SHORT).show();
            //to restart activity or refresh
            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}