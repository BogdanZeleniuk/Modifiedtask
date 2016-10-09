package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 Object that will be used to get information from console.
 If there is no data, than check other source (File).
 */

public class ConsoleReader{

    public static ArrayList<String> dataList = new ArrayList<>();

    public void readInformation() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        try {
            while (true) {
                line = bufferedReader.readLine();
                if (line.isEmpty() || line.equals("")) {
                    break;
                }
                else
                    dataList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Put info into console line!");
        }
    }

    public ConsoleReader() {
    }
}
