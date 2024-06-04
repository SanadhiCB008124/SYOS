package DatabaseConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfigurations {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = DatabaseConfigurations.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (inputStream == null) {
                System.out.println("Sorry, could not find database.properties");
                System.exit(1);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDatabaseUser() {
        return properties.getProperty("db.username");
    }

    public static String getpassword() {
        return properties.getProperty("db.password");
    }
}
