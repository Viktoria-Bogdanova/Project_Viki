package POM.pages;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataProviders {
//to read from csv file
    @DataProvider(name = "correctUsers")
    public Object[][] correctUsers() {
        //make list for data
        List<Object[]> data = new ArrayList<>();
//open csv file from directory
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("wrongUsers.csv")))) {
//read the lines from csv
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    data.add(new Object[]{parts[0].trim(), parts[1].trim()});
                }
            }
//to show message if don't find the file
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Файлът wrongUsers.csv не е намерен или не може да бъде прочетен.");
        }
//Return data as a two-dimensional array
        return data.toArray(new Object[0][0]);
    }
}
