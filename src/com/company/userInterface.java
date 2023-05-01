package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class userInterface {

    ArrayList<account> accounts;
    Scanner INPUT;

    userInterface() {
        accounts=saveLoad.load();
        //accounts = new ArrayList();
        INPUT = new Scanner(System.in);
    }

    void addAccount(String accountName, double money, String password) {
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

    account selectItem(String word){
        System.out.print("Account to be " + word + "\n>");
        String targetName = INPUT.nextLine();
        return selectAccount(targetName);
    }


    void removeAccount(String accountName) {
        account target = selectAccount(accountName);
        if (target != null) {
            accounts.remove(target);
        } else {
            System.out.println("The account was not found");
        }
    }


    void editAccount(String accountName, double money, String password) {
        removeAccount(accountName);
        addAccount(accountName,money,password);
    }

    void displayAccounts(){
        for (account account:accounts){
            System.out.println(account.getAccountName());
        }
    }

    boolean passwordAuthentication(String accountName, Scanner INPUT){
        account target=selectAccount(accountName);
        if (target!=null) {
            System.out.print("What is the password for this account\n>");
            if (INPUT.nextLine().equals(target.password)) {
                return true;
            } else {
                System.out.println("The password is incorrect");
                return false;
            }
        }
            System.out.println("The account named "+accountName+" can't be found");
            return false;

    }

    void UILoop(){
        while(true){
            String[] options={"Add account", "Remove account", "Edit account", "Withdraw cash", "Deposit",  "Account Balance", "List accounts", "Quit"};
            int optionNum=toolBox.consoleOptions(options,INPUT);

            if(optionNum== options.length-1){
                return;
            }
            else if(optionNum==0){
                String accountName=toolBox.askAccountName("add",INPUT);
                double money=toolBox.askMoneyAmount(INPUT);
                String password = toolBox.askPassword(INPUT);
                addAccount(accountName,money,password);
            }
            else if(optionNum==1){
                String accountName=toolBox.askAccountName("remove",INPUT);
                if(passwordAuthentication(accountName,INPUT)){
                    removeAccount(accountName);
                }
            }
            else if(optionNum==2){
                String accountName=toolBox.askAccountName("edited",INPUT);
                if(passwordAuthentication(accountName,INPUT)) {
                    double money = toolBox.askMoneyAmount(INPUT);
                    String password = toolBox.askPassword(INPUT);
                    editAccount(accountName, money, password);
                }
            }


            else if(optionNum==3){
                String accountName=toolBox.askAccountName("withdrawn from",INPUT);
                if(passwordAuthentication(accountName,INPUT)){
                    account target = selectAccount(accountName);
                    toolBox.changeCash(-1, target, INPUT);
                }
            }
            else if(optionNum==4){
                account target=selectItem("Deposit to");
                toolBox.changeCash(1,target,INPUT);
            }
            else if(optionNum==5){
                displayAccounts();
            }
            else if(optionNum==6){
                account target=selectItem("previewed");
                if(target!=null) {
                    if(passwordAuthentication(target.accountName, INPUT)){
                        System.out.println("Account name: " + target.accountName + "  Account Balance: " + target.money + "\n");
                    }
                }
                else {
                    System.out.println("The account you are trying to look for seems to not exist with the data base\n");
                }
            }

            saveLoad.save(accounts); 
        }
    }

}