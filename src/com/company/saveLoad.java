//Owen Riske

package com.company;


import java.util.ArrayList;

public class saveLoad {


    public static void save(ArrayList<account> toBeSaved) {
        //preset variables
        String accountName = "";
        String money = "";
        String password = "";

        //seperate the accountName, amount of money, and password from each other
        for (account item : toBeSaved) {
            accountName+=item.accountName+"\n";
            money+=item.money+"\n";
            password+=item.password+"\n";
        }

        //save the account name, amount of money, and password from each other
        file.writeFile("Account Name",accountName);
        file.writeFile("Money", money);
        file.writeFile("Password", password);
    }

    public static ArrayList<account> load() {
        //preset variable
        ArrayList<account> output = new ArrayList<>();

        //get the contents of each file and split it into an array for each
        String[] accountName = splitLoad(file.readFile("Account Name"));
        String[] money = splitLoad(file.readFile("Money"));
        String[] password = splitLoad(file.readFile("Password"));

        //loop so every account can be made
        for (int i = 0; i < (accountName.length + money.length + password.length) / 3; i++) {
            //make a temporary account from the arrays
            account temp = new account(accountName[i], toolBox.makeDouble(money[i]), password[i]);
            //added the temporary account to the output Arraylist
            output.add(temp);
        }
        return output;


    }

    private static String[] splitLoad(String input){
        //remove the two [] ends and split the input on each comma
        return input.replace("]","").replace("[","").split(", ");
    }
}