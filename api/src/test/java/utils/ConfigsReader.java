package utils;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigsReader {

    private final Properties properties;
    private static ConfigsReader instance = null;

    @SneakyThrows
    public ConfigsReader() {
        properties = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/properties/configuration.properties");
        properties.load(fis);
    }

    public static synchronized ConfigsReader get() {
        if (instance == null) {
            instance = new ConfigsReader();
        }
        return instance;
    }

    public String value(String key) {
        return this.properties.getProperty(key, String.format("The key %s does not exists!", key));
    }

}
