package org.sample.info;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.LinkedHashMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BuildInfo {

  public static final String FILENAME = "build-info.properties";
  // Maintain insertion order
  private Map<String, String> info = Collections.synchronizedMap(new LinkedHashMap<>());

  public BuildInfo() {
    InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream(FILENAME);
    if (propertiesStream != null) {
      Properties properties = new Properties();
      try {
        properties.load(propertiesStream);

        info.put("group", properties.getProperty("build.group"));
        info.put("artifact", properties.getProperty("build.artifact"));
        info.put("name", properties.getProperty("build.name"));
        info.put("version", properties.getProperty("build.version"));
        info.put("time", properties.getProperty("build.time"));
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
