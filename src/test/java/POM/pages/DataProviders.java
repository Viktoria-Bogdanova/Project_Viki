package POM.pages;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataProviders {

    @DataProvider(name = "correctUsers")
    public Object[][] correctUsers() {
        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("wrongUsers.csv")))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    data.add(new Object[]{parts[0].trim(), parts[1].trim()});
                }
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Файлът wrongUsers.csv не е намерен или не може да бъде прочетен.");
        }

        return data.toArray(new Object[0][0]);
    }
}
