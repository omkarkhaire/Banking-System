package com.example.bankingsystem;

public class customermodel {

    String name,emailid,ifsc_code,accno,mobileno;
    int id,balance;

    public customermodel()
    {
    }



    public customermodel(int id, String name, String emailid, String ifsc_code, String accno, String mobileno, int balance) {
         this.id=id;
        this.name = name;
        this.emailid = emailid;
        this.ifsc_code = ifsc_code;
        this.accno = accno;
        this.mobileno = mobileno;
        this.balance = balance;
    }
    public String toString() {
        return
                "id="+id+"\nname="+name+"\n"+"mobile="+mobileno+"\nbalance="+balance;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

