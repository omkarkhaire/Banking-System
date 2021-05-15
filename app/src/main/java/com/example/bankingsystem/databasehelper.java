package com.example.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class databasehelper extends SQLiteOpenHelper {

    public static final String tablename="customer_table";
    public static final String cname="name";
    public static final String cmno="mobileno";
    public static final String email="email";
    public static final String accno="accno";
    public static final String ifsc="ifsc";
    public static final String balance="balance";
    public static final String Id="ID";

//    for transcation history table
    public static final String thtable="thtable";
    public static final String thid="thid";
    public static final String from="form";
    public static final String trperson="trperson";
    public static final String amount="amount";


    public databasehelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    //first time we access database object
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createtable="create table "+tablename+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+cname+" text,"+cmno+" text unique,"+email+" text,"+accno+" text unique,"+ifsc+" text,"+balance+" int)";
        sqLiteDatabase.execSQL(createtable);

//        mobile and account_No must be unique
//        for transaction history table

        String newtable="create table "+thtable+"(thid INTEGER PRIMARY KEY AUTOINCREMENT, "+from+" text not null,"+trperson+" text not null,"+amount+" int)";
        sqLiteDatabase.execSQL(newtable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);

    }


    public boolean addrecort(customermodel model)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();  //use this

        cv.put(cname,model.getName());
        cv.put(cmno,model.getMobileno());
        cv.put(email,model.getEmailid());
        cv.put(accno,model.getAccno());
        cv.put(ifsc,model.getIfsc_code());
        cv.put(balance,model.getBalance());

        long insert = db.insert(tablename, null, cv);

        if(insert==-1)
        {
            return  false;
        }

        return  true;

    }
    public ArrayList<customermodel> geteverything(){

        ArrayList<customermodel> returnlist=new ArrayList<>();

        String display="select * from "+tablename;

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor = db.rawQuery(display,null);
        if(cursor.moveToFirst())
        {//if result or data rows are present  as return cursor data
            do{
//                int cid=cursor.getInt(0);
//                String cname=cursor.getString(1);
//                int cage=cursor.getInt(2);
//                boolean cactive=cursor.getInt(3)==1?true:false;
                int id=cursor.getInt(0);
                String name=cursor.getString(1);
                String mobile=cursor.getString(2);
                String email=cursor.getString(3);
                String acno=cursor.getString(4);
                String ifsc=cursor.getString(5);
                int balanc=cursor.getInt(6);



                customermodel newcustomer=new customermodel(id,name,email,ifsc,acno,mobile,balanc);
                returnlist.add(newcustomer);

            }while (cursor.moveToNext());

        }
        else
        {
            //not add anything
        }
        cursor.close(); //imp
        db.close();
        return returnlist;

    }

    public void debit(int transferfrom_amount,int id)
    {
        //minus
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE "+tablename+" set "+balance+" = "+balance+" - "+transferfrom_amount+" where "+Id+" = "+id;
        db.execSQL(query);

    }
    public void credit(int transferfrom_amount,int id)
    {
        //add
        SQLiteDatabase db=this.getWritableDatabase();
        String query="UPDATE "+tablename+" set "+balance+" = "+balance+" + "+transferfrom_amount+" where "+Id+" = "+id;
        db.execSQL(query);

    }

    public boolean addrecorttoth(customermodel model)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();  //use this

        cv.put(from,model.getName());
        cv.put(trperson,model.getEmailid());
        cv.put(amount,model.getBalance());

        long insert = db.insert(thtable, null, cv);

        if(insert==-1)
        {
            return  false;
        }

        return  true;

    }
    public ArrayList<customermodel> getdatafromtransaction()
    {
        ArrayList<customermodel> returnlist=new ArrayList<>();

        String display="select * from "+thtable;

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor = db.rawQuery(display,null);
        if(cursor.moveToFirst())
        {//if result or data rows are present  as return cursor data
            do{
//                int cid=cursor.getInt(0);
//                String cname=cursor.getString(1);
//                int cage=cursor.getInt(2);
//                boolean cactive=cursor.getInt(3)==1?true:false;
                int id=cursor.getInt(0);
                String from=cursor.getString(1);
                String toperson=cursor.getString(2);
                int amount=cursor.getInt(3);



                customermodel newcustomer=new customermodel(id,from,toperson,"null","null","null",amount);
                returnlist.add(newcustomer);

            }while (cursor.moveToNext());

        }
        else
        {
            //not add anything
        }
        cursor.close(); //imp
        db.close();
        return returnlist;


    }
    public void cleartransaction()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String clear="DELETE FROM "+thtable;
        db.execSQL(clear);


    }

}
