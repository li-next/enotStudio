package studio.enot;

import okio.Utf8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ReadProperties {

    protected static FileInputStream fileInputStream;
    protected static Properties properties;
    static {
        try {
            BufferedReader fileInputStream = new BufferedReader(new InputStreamReader(new FileInputStream("/home/samsung/IdeaProjects/enotStudio/src/test/java/properties/system.properties"), "UTF-8"));
            //fileInputStream = new FileInputStream("/home/samsung/IdeaProjects/enotStudio/src/test/java/properties/system.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace(); } } }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
