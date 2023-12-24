package id.ac.pnb.SnakeUp.helpers;

import id.ac.pnb.SnakeUp.SnakeUp;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

  private final static Properties properties = new Properties();

  public static String get(String key) {

    return properties.getProperty(key);
  }

  public static void load(String path) {
    try {
      properties.load(SnakeUp.class.getClassLoader()
          .getResourceAsStream(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
