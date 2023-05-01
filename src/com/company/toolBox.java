package com.company;

import java.util.Scanner;

public class toolBox {

    public static int consoleOptions(String[] options, Scanner INPUT){
        displayArray(options);
        String userInput=INPUT.nextLine();

        try{
            userInput.replace(".","");
            if(Integer.parseInt(userInput)<=options.length && Integer.parseInt(userInput)>=0){
                return Integer.parseInt(userInput)-1;
            }
        }
        catch (Exception e){
            for (int i = 0; i < options.length; i++) {
                if(userInput.equalsIgnoreCase(options[i])){
                    return i;
                }
            }
        }
        System.out.println(userInput+" isn't an option that the program can understand");
       return -1;
    }

    public static void displayArray(String[] array){
        for (int i = 1; i < array.length; i++) {
            System.out.print(i+". "+array[i-1]+", ");
        }
        System.out.print(array.length+". "+array[array.length-1]+"\n>");
    }
    public static double makeSureIsInteger(String input){
       double output=Double.NaN;
        try {

             output = Double.parseDouble(input);
            return output;
        }
        //tells if input isn't an int
        catch (Exception e) {
            System.out.println("Sorry but an integer needs to be inputted\n");
        }
        return output;

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
        if(target!=null){
            double money=makeSureInputisInteger("Amount of money in the change?\n>",INPUT);
            target.changeCash(money*moneyAdjustment);
        }
        else{
            System.out.println("Account couldn't be found");
        }
    }


    public static String askAccountName(String actionToAccount, Scanner INPUT){
        System.out.print("What is the name of the account you would like to "+actionToAccount+"?\n>");
        return INPUT.nextLine();
    }
    public static double askMoneyAmount(Scanner INPUT){
        return makeSureInputisInteger("What is the amount of money in the account?\n>", INPUT);
    }

    public static String askPassword(Scanner INPUT){
        System.out.print("What is the account's password?\n>");
        return INPUT.nextLine();
    }

    public static double makeDouble(String input){
        double output=0d;

        try{
            output=Double.parseDouble(input);
        } catch (Exception e) {
            System.out.println(input+" isn't a number");
        }
        return output;
    }
}
