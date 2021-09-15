package org.sample.info;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.LinkedHashMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GitInfo {

  public static final String FILENAME = "git.properties";
  // Maintain insertion order
  private Map<String, Object> info = Collections.synchronizedMap(new LinkedHashMap<>());

  public GitInfo() {
    InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream(FILENAME);
    if (propertiesStream != null) {
      Properties properties = new Properties();
      try {
        properties.load(propertiesStream);

        Map<String, Object> commit = Collections.synchronizedMap(new LinkedHashMap<>());
        commit.put("time", properties.getProperty("git.commit.time"));
        commit.put("id", properties.getProperty("git.commit.id.abbrev"));
        info.put("commit", commit);
        info.put("branch", properties.getProperty("git.branch"));
      }
      catch (Exception e) {
        throw new RuntimeException("Invalid " + FILENAME);
      }
    }
  }

  public Map<String, Object> getInfo() {
    return info;
  }
}
