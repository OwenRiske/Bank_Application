//Owen Riske

package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


//from: https://www.geeksforgeeks.org/file-handling-in-java/
public class file {

    public static File makeFile(String fileName) {
        File file = null;
        //try making a new file
        try {
            file = new File(fileName + ".txt");
            file.createNewFile();

        }
        //if can't through an error
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return file;
    }

    public static void writeFile(String fileName, String fileContents) {
        //try to input fileContents to the file
        try {
            //get the file
            FileWriter myWriter = new FileWriter(fileName + ".txt");
            //write the information
            myWriter.write(fileContents);
            myWriter.close();
        }
        //if can't through error
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String readFile(String fileName) {
        //preset variable
        ArrayList output=new ArrayList();

        //try getting contents from the file
        try {
            //get the file
            File file = makeFile(fileName);
            Scanner myReader = new Scanner(file);
            //add each line of the file to the arrayList
            while (myReader.hasNextLine()) {
                output.add(myReader.nextLine());
            }
            myReader.close();
        }
        //if can't through error
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //return the file contents as a string
        return String.valueOf(output);
    }

}
