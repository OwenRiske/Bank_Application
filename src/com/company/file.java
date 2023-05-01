package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class file {

    public static File makeFile(String fileName) {
        File file = null;
        try {
            file = new File(fileName + ".txt");
            file.createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return file;
    }

    public static void writeFile(String fileName, String fileContents) {
        try {
            FileWriter myWriter = new FileWriter(fileName + ".txt");
            myWriter.write(fileContents);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String readFile(String fileName) {
        ArrayList output=new ArrayList();
        try {
            File file = makeFile(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                output.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return String.valueOf(output);
    }

}
