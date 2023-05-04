//Owen Riske

package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class userInterface {


    ArrayList<account> accounts;
    Scanner INPUT;

    userInterface() {
        //load the information upon start up of the program
        accounts=saveLoad.load();
        //accounts = new ArrayList();
        INPUT = new Scanner(System.in);
    }

    void addAccount(String accountName, double money, String password) {
        //make a new account
        account temp = new account(accountName, money, password);
        accounts.add(temp);
    }

    account selectAccount(String accountName) {
        account target = null;

        //Find target in list
        for (account item : accounts) {
            if (item.getAccountName().equalsIgnoreCase(accountName)) {
                target = item;//Found it
            }
        }
        return target;
    }

    account userInputSelecAccount(String word){
        //get the user's input to get the account
        System.out.print("Account to be " + word + "?\n>");
        String targetName = INPUT.nextLine();
        return selectAccount(targetName);
    }


    void removeAccount(String accountName) {
        //get the account
        account target = selectAccount(accountName);

        //if the account exists then remove it
        if (target != null) {
            accounts.remove(target);
        }
        //otherwise tell the user that it doesn't exist
        else {
            System.out.println("The account was not found");
        }
    }


    void editAccount(String accountName, double money, String password) {
        //to edit just remove the account and make a new one
        removeAccount(accountName);
        addAccount(accountName,money,password);
    }

    void displayAccounts(){
        //print the name of each account
        for (account account:accounts){
            System.out.println(account.getAccountName());
        }
    }

    boolean passwordAuthentication(String accountName, Scanner INPUT){
        //find the account
        account target=selectAccount(accountName);
        //if it exists
        if (target!=null) {
            //get user to input the password
            System.out.print("What is the password for this account\n>");
            //if the password is correct then return true
            if (INPUT.nextLine().equals(target.password)) {
                return true;
            }
            //otherwise return false
            else {
                System.out.println("The password is incorrect");
                return false;
            }
        }
        //tell user if the account couldn't be found
            System.out.println("The account named "+accountName+" can't be found");
            return false;

    }

    //user Menu
    void UILoop(){
        while(true){
            //array of the options the main menu has
            String[] options={"Add account", "Remove account", "Edit account", "Withdraw cash", "Deposit",  "Account Balance", "List accounts", "Quit"};
            //get the option the user chose
            int optionNum=toolBox.consoleOptions(options,INPUT);

            //return if the user quits
            if(optionNum== options.length-1){
                return;
            }
            //add account
            else if(optionNum==0){
                String accountName=toolBox.askAccountName("add",INPUT);
                double money=toolBox.askMoneyAmount(INPUT);
                String password = toolBox.askPassword(INPUT);
                addAccount(accountName,money,password);
            }
            //removes account
            else if(optionNum==1){
                String accountName=toolBox.askAccountName("remove",INPUT);
                if(passwordAuthentication(accountName,INPUT)){
                    removeAccount(accountName);
                }
            }
            //edits account
            else if(optionNum==2){
                String accountName=toolBox.askAccountName("edited",INPUT);
                if(passwordAuthentication(accountName,INPUT)) {
                    double money = toolBox.askMoneyAmount(INPUT);
                    String password = toolBox.askPassword(INPUT);
                    editAccount(accountName, money, password);
                }
            }
            //withdraws from account
            else if(optionNum==3){
                String accountName=toolBox.askAccountName("withdrawn from",INPUT);
                if(passwordAuthentication(accountName,INPUT)){
                    account target = selectAccount(accountName);
                    toolBox.changeCash(-1, target, INPUT);
                }
            }
            //deposit to account
            else if(optionNum==4){
                account target=userInputSelecAccount("Deposit to");
                toolBox.changeCash(1,target,INPUT);
            }
            //check balance of one account
            else if(optionNum==5) {
                account target = userInputSelecAccount("previewed");
                if (target != null) {
                    if (passwordAuthentication(target.accountName, INPUT)) {
                        System.out.println("Account name: " + target.accountName + "  Account Balance: " + target.money + "\n");
                    }
                }
            }
            //display all the accounts
            else if(optionNum==6){
                displayAccounts();
            }
                //tell user if the chose wasn't one the program understood
                else {
                    System.out.println("The account you are trying to look for seems to not exist with the data base\n");
                }
                //save all accounts data
            saveLoad.save(accounts);
        }

        }
    }

