package org.sample.info;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.LinkedHashMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppInfo {

  public static final String FILENAME = "application.properties";
  // Maintain insertion order
  private Map<String, String> info = Collections.synchronizedMap(new LinkedHashMap<>());

  public AppInfo() {
    InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream(FILENAME);
    if (propertiesStream != null) {
      Properties properties = new Properties();
      try {
        properties.load(propertiesStream);

        info.put("name", properties.getProperty("info.app.name"));
        info.put("description", properties.getProperty("info.app.description"));
        info.put("version", properties.getProperty("info.app.version"));
      }
      catch (Exception e) {
        throw new RuntimeException("Invalid " + FILENAME);
      }
    }
  }

  public Map<String, String> getInfo() {
    return info;
  }
}
