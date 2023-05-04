//Owen Riske

package com.company;

import java.util.Scanner;

public class toolBox {

    public static int consoleOptions(String[] options, Scanner INPUT){
        //user's inputted option
        displayArray(options);
        String userInput=INPUT.nextLine();

        //get the option correlated to the user's input
        try{
            //remove period if there is one
            userInput.replace(".","");
            //make sure that the inputted number is on the array
            if(Integer.parseInt(userInput)<=options.length && Integer.parseInt(userInput)>=0){
                return Integer.parseInt(userInput)-1;
            }
        }
        catch (Exception e){
            //if not a number than check to see if it is one of the options, if it is return which one it is
            for (int i = 0; i < options.length; i++) {
                if(userInput.equalsIgnoreCase(options[i])){
                    return i;
                }
            }
        }
        //tell user that the option they selected wasn't an option the program understood
        System.out.println(userInput+" isn't an option that the program can understand");
       return -1;
    }

    public static void displayArray(String[] array){
        //print each part of the array but the last part
        for (int i = 1; i < array.length; i++) {
            System.out.print(i+". "+array[i-1]+", ");
        }
        //print last part separate
        System.out.print(array.length+". "+array[array.length-1]+"\n>");
    }

    public static double makeSureInputisInteger(String message, Scanner INPUT) {
        //make it loop until input is an int
        while (true) {
            try {
                System.out.print(message);

                int output = INPUT.nextInt();
                //clear INPUT
                INPUT.nextLine();
                return output;
            }
            //tells if input isn't an int
            catch (Exception e) {
                //clear INPUT
                INPUT.nextLine();
                System.out.println("Sorry but an integer needs to be inputted\n");
            }
        }
    }

    public static void changeCash(int moneyAdjustment, account target, Scanner INPUT){
        //if the target is saved
        if(target!=null){
            //get the amount of money to be changed
            double money=makeSureInputisInteger("Amount of money in the change?\n>",INPUT);
            //change the account's cash, the moneyAdjustment determines if it is a withdrawal or deposit
            target.changeCash(money*moneyAdjustment);
        }
        //otherwise tell the user that the account doesn't exist
        else{
            System.out.println("Account couldn't be found");
        }
    }


    public static String askAccountName(String actionToAccount, Scanner INPUT){
        //get the account Name
        System.out.print("What is the name of the account you would like to "+actionToAccount+"?\n>");
        return INPUT.nextLine();
    }
    public static double askMoneyAmount(Scanner INPUT){
        //get the money
        return makeSureInputisInteger("What is the amount of money in the account?\n>", INPUT);
    }

    public static String askPassword(Scanner INPUT){
        //get the password
        System.out.print("What is the account's password?\n>");
        return INPUT.nextLine();
    }

    public static double makeDouble(String input){
        //preset variable
        double output=0d;

        //try to change the input into a double
        try{
            output=Double.parseDouble(input);
        }
        //if it can't tell the user
        catch (Exception e) {
            System.out.println(input+" isn't a number");
        }
        return output;
    }
}
