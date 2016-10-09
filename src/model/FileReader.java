package model;


import java.io.*;
import java.util.ArrayList;

/**
 Object that will be used to get information from file (in my way it is 'data.txt').
 If there is no data, than throw an exception.
 */
public class FileReader{

    public static ArrayList<String> dataList = new ArrayList<>();

    public void readInformation() {

        BufferedReader bufferedReader = null;
        try {
            String fileName = "D:\\Project\\Modifiedtask\\data.txt";
            bufferedReader = new BufferedReader(new java.io.FileReader(new File(fileName)));
            String line;
            while ((line = bufferedReader.readLine()) !=null){
                dataList.add(line);
            }
        } catch (IOException e) {
            System.out.println("There is no data in file/not find this file or something else.");
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("Exception in " + e.getMessage());
            }
        }
    }

    public FileReader() {
    }
}
