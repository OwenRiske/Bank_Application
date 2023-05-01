package com.company;


public class account {
    String accountName;
    double money;
    String password;

    account(String accountName,double money, String password){
        this.accountName=accountName;
        this.money=money;
        this.password=password;
    }

    void changeCash(double money){
        if(this.money+money>=0) {
            this.money += money;
        }
        else{
            System.out.println("Not enough money in the account for the withdrawal");
        }
    }



    String getAccountName(){
        return accountName;
    }
}
