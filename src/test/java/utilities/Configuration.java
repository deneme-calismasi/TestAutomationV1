package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class Configuration {

    private static final String PROPERTIES_FILE = "config.properties";
    private Properties props = null;

    public Configuration() {
        loadProperties(PROPERTIES_FILE);
    }

    private void loadProperties(String filename) {
        props = new Properties();
        InputStream stream;
        stream = ClassLoader.getSystemResourceAsStream("config.properties");

        if (stream == null) {
            System.err.println("Unable to open properties file " + filename);
            return;
        }

        try {
            Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            props.load(reader);
        } catch (IOException e) {
            System.err.println("Error reading properties file " + filename);
            System.err.println(e.getMessage());
        }

        try {
            stream.close();
        } catch (IOException ignored) {
        }

    }


    public String getProperty(String name) {
        return props.getProperty(name);
    }
}
