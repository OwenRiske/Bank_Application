package com.company;


import java.util.ArrayList;

public class saveLoad {


    public static void save(ArrayList<account> toBeSaved) {
        String accountName = "";
        String money = "";
        String password = "";


        for (account item : toBeSaved) {
            accountName+=item.accountName+"\n";
            money+=item.money+"\n";
            password+=item.password+"\n";
        }

        file.writeFile("Account Name",accountName);
        file.writeFile("Money", money);
        file.writeFile("Password", password);
    }

    public static ArrayList<account> load() {
        ArrayList<account> output = new ArrayList<>();
        String[] accountName = splitLoad(file.readFile("Account Name"));
        String[] money = splitLoad(file.readFile("Money"));
        String[] password = splitLoad(file.readFile("Password"));

        for (int i = 0; i < (accountName.length + money.length + password.length) / 3; i++) {
            account temp = new account(accountName[i], toolBox.makeDouble(money[i]), password[i]);
            output.add(temp);
        }
        return output;


    }

    private static String[] splitLoad(String input){
        return input.replace("]","").replace("[","").split(", ");
    }
}