package com.example.bankingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class allcustomers extends AppCompatActivity {
    customermodel model;
    RecyclerView list;
   static ArrayList<customermodel> records=new ArrayList<>();
   static ArrayList<Integer> imagecollection;


    ArrayAdapter <customermodel> adapter;
    databasehelper helper;
    MyCustomAdapter myCustomAdapter;
   static ArrayList<customermodel> displaylist;
   //from database very important
   public boolean onCreateOptionsMenu(Menu menu) {

       MenuInflater menuInflater=getMenuInflater();
       menuInflater.inflate(R.menu.itemmenu,menu);


       return super.onCreateOptionsMenu(menu);
   }
    //
//  when  we select menu options



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcustomers);
        list=findViewById(R.id.list);
        helper=new databasehelper(getApplicationContext());


        imagecollection=new ArrayList<>();

        imagecollection.add(R.drawable.arjun);
        imagecollection.add(R.drawable.dhruv);
        imagecollection.add(R.drawable.shivangi);
        imagecollection.add(R.drawable.kiaan);
        imagecollection.add(R.drawable.raghav);
        imagecollection.add(R.drawable.neha);
        imagecollection.add(R.drawable.kabir);
        imagecollection.add(R.drawable.kavin);
        imagecollection.add(R.drawable.rhea);
        imagecollection.add(R.drawable.ishita);

        display(helper);



        getSupportActionBar().setTitle("All Customers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//home btn
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            databaseSetup();

            // mark first time has ran.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
    }

    private void databaseSetup() {
        records.add(new customermodel(1,"Arjun","Arjun22@gmail.com","SBIN0000321","XXXX7357"," 6378612687",12000));
        records.add(new customermodel(2,"Dhruv","Dhruv@gmail.com","SBIN0000121","XXXX7378"," 9888612687",2000));
        records.add(new customermodel(3,"Shivangi","shivangi@gmail.com","SBIN0000100","XXXX9378"," 9888612976",4000));
        records.add(new customermodel(4,"Kiaan","Kiaan@outlook.com","SBIN0004521","XXXX76157"," 9822920918",22000));
        records.add(new customermodel(5,"Raghav","Raghav@outlook.com","SBIN0004500","XXXX78265"," 7796178993",5000));
        records.add(new customermodel(6,"Neha","Nehu11@outlook.com","SBIN0004508","XXXX90265"," 7796179822",15000));
        records.add(new customermodel(7,"Kabir","Kabir@outlook.com","SBIN0004506","XXXX90256"," 7796179228",5000));
        records.add(new customermodel(8,"Kavin","Kavin45@outlook.com","SBIN0004509","XXXX90254"," 7796179223",2000));
        records.add(new customermodel(9,"Rhea","Rhea9@gmail.com","SBIN0004506","XXXX90259"," 7796179224",9000));
        records.add(new customermodel(10,"Ishita","ishu@gmail.com","SBIN0004560","XXXX90250"," 7796179242",19000));


        for(int i=0;i<records.size();i++)
        {
            databasehelper helper=new databasehelper(getApplicationContext());
            boolean status=helper.addrecort(records.get(i));
            if(status)
            {

            }

        }



    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
          startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        if(item.getItemId()==R.id.alltransaction)
        {
            startActivity(new Intent(getApplicationContext(),transactionhistory.class));
        }

        return super.onOptionsItemSelected(item);
    }
    public void display(databasehelper helper2) {
//        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, helper2.geteverything());
//        list.setAdapter(adapter);



        displaylist= helper2.geteverything();
        list.setHasFixedSize(true);
        list.setItemViewCacheSize(20);
        list.setDrawingCacheEnabled(true);
        list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        list.setLayoutManager(new LinearLayoutManager(this));//most important
        MyCustomAdapter adapter=new MyCustomAdapter(getApplicationContext(),displaylist,imagecollection);
        list.setAdapter(adapter);



//        myCustomAdapter=new MyCustomAdapter(getApplicationContext(),displaylist);
//        list.setAdapter(myCustomAdapter);
    }
}