package factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationConfiguration {
    public static String getPropertyDAO() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("daotype");
    }
}
