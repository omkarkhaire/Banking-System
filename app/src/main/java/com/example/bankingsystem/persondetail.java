package com.example.bankingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.bankingsystem.allcustomers.displaylist;
import static com.example.bankingsystem.allcustomers.imagecollection;

public class persondetail extends AppCompatActivity {

    int position;//user from
    int selectposition=-1; //to transfer to
    int moneytotransfer=-1;

    int transferamout=-1;

    TextView name,mobile,email,account,ifsc,balance;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persondetail);
        img=findViewById(R.id.personimgindetails);
        name=findViewById(R.id.personnameindetail);
        mobile=findViewById(R.id.personnoindetail);
        email=findViewById(R.id.personmailindetail);
        account=findViewById(R.id.personaccnoindetail);
        ifsc=findViewById(R.id.personifscindetail);
        balance=findViewById(R.id.personbalanceindetail);

        Intent intent=getIntent();
        position=intent.getIntExtra("location",-1);
        selectposition=intent.getIntExtra("selected",-100);
        transferamout=intent.getIntExtra("amouttotransfer",-1);

        if(selectposition!=-100 && transferamout!=-1)
        {
            String nametotransfer=displaylist.get(selectposition).getName().toString();
            Toast.makeText(getApplicationContext(),"Money Transfered Successfully to "+nametotransfer,Toast.LENGTH_LONG).show();
            int transferfrom=displaylist.get(position).getId();
            int transferto=displaylist.get(selectposition).getId();

            debit(transferfrom);//touchs and update database
            credit(transferto);//touchs and update database

            databasehelper pleasehelp2=new databasehelper(getApplicationContext());
            ArrayList<customermodel> list2=pleasehelp2.geteverything();
            balance.setText(" Rs "+list2.get(position).getBalance()+"/-");

            customermodel transactionobj=new customermodel(0,displaylist.get(position).getName(),displaylist.get(selectposition).getName(),"null","null","null",transferamout);
            databasehelper transferhelper=new databasehelper(getApplicationContext());
            transferhelper.addrecorttoth(transactionobj);


        }



        if(position!=-1)
        {
            databasehelper pleasehelp=new databasehelper(getApplicationContext());
            ArrayList<customermodel> list=pleasehelp.geteverything();
            if(list.get(position).getName().equals("Kabir") ||list.get(position).getName().equals("Kavin") ||list.get(position).getName().equals("Kiaan"))
            {

                img.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            img.setImageResource(imagecollection.get(position));
            name.setText(list.get(position).getName().toString());
            mobile.setText(list.get(position).getMobileno().toString());
            email.setText(list.get(position).getEmailid().toString());
            account.setText(list.get(position).getAccno().toString());
            ifsc.setText(list.get(position).getIfsc_code().toString());
            balance.setText(" Rs "+list.get(position).getBalance()+"/-");
        }
        else{
            Toast.makeText(getApplicationContext(),"-1 occured",Toast.LENGTH_LONG).show();
        }

        getSupportActionBar().setTitle("Customer Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//home btn
        getSupportActionBar().setDisplayShowHomeEnabled(true);



    }

    private void credit(int transferto) {
//        add
        databasehelper helper=new databasehelper(getApplicationContext());
        helper.credit(transferamout,displaylist.get(selectposition).getId());



    }

    private void debit(int transferfrom) {
        //minus
        databasehelper helper=new databasehelper(getApplicationContext());
        helper.debit(transferamout,displaylist.get(position).getId());

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

        return super.onOptionsItemSelected(item);
    }

    public void executetransfer(View view) {

        AlertDialog.Builder mydialog=new AlertDialog.Builder(this);
        mydialog.setTitle("Enter Amount To Transfer");

        EditText editText=new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mydialog.setView(editText);

        mydialog.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(editText.getText().toString().equals("") || editText.getText().toString().equals(null))//null string
                {
                    editText.setError("Please enter amount");
                    Toast.makeText(persondetail.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();

                }
                else {
                    moneytotransfer = Integer.parseInt(editText.getText().toString().trim());


                    if (moneytotransfer <= displaylist.get(position).getBalance() && moneytotransfer > 0) {
                        selectcustomertotransfer();


                    } else if (moneytotransfer > displaylist.get(position).getBalance()) {
                        editText.setError("Insufficient Balance");
                        Toast.makeText(persondetail.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                    } else {
                        editText.setError("Please enter valid amount");
                        Toast.makeText(persondetail.this, "Please enter valid amount", Toast.LENGTH_SHORT).show();

                    }
                }
//                Toast.makeText(persondetail.this, ""+moneytotransfer, Toast.LENGTH_SHORT).show();
            }
        });
        mydialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                moneytotransfer=-1;
                Toast.makeText(getApplicationContext(),"Transaction Cancelled",Toast.LENGTH_LONG).show();
            }
        });
        
        mydialog.show();

//        if(moneytotransfer<=displaylist.get(position).getBalance() && moneytotransfer>=0 )
//        {
//            selectcustomertotransfer();
//        }







//        Intent intent=new Intent(getApplicationContext(),selectcustomer.class);
//        intent.putExtra("prevlocation",position);
//        startActivity(intent);

    }

    private void selectcustomertotransfer() {
        Intent intent=new Intent(getApplicationContext(),selectcustomer.class);
        intent.putExtra("prevlocation",position);
        intent.putExtra("amouttotransfer",moneytotransfer);
        startActivity(intent);
    }
}