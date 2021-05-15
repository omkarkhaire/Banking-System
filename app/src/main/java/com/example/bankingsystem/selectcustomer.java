package com.example.bankingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class selectcustomer extends AppCompatActivity {
    RecyclerView list;
    databasehelper helper2;
    selectadapter adapter;
    int transferamount=-1;
    static ArrayList<customermodel> records2=new ArrayList<>();
    int prevlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcustomer);

        list=findViewById(R.id.list);


        Intent intent=getIntent();
        prevlocation=intent.getIntExtra("prevlocation",-1);
        transferamount=intent.getIntExtra("amouttotransfer",-1);

        getSupportActionBar().setTitle("Select Person");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//home btn
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        helper2=new databasehelper(getApplicationContext());

        records2=helper2.geteverything();

        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));//most important
        adapter=new selectadapter(getApplicationContext(),records2,prevlocation,transferamount);
        list.setAdapter(adapter);




    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}