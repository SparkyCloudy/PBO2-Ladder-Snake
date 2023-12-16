package id.ac.pnb.SnakeUp.helpers;

import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

  private final static Properties properties = new Properties();

  public static String get(String key) {
    try {
      _initialize();
    } catch (IOException ignored) {}

    return properties.getProperty(key);
  }

  private static void _initialize() throws IOException {
    properties.load(PropertiesHelper.class.getClassLoader()
        .getResourceAsStream("/configs/games.properties"));
  }
}
