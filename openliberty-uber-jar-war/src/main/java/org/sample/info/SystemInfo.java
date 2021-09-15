package org.sample.info;

import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SystemInfo {

  // Maintain insertion order
  private Map<String, Object> info = Collections.synchronizedMap(new LinkedHashMap<>());

	@Inject
	AppInfo appInfo;

  @Inject
	GitInfo gitInfo;

  @Inject
	BuildInfo buildInfo;

  public SystemInfo() {}

  @Inject
  public SystemInfo(AppInfo appInfo, GitInfo gitInfo, BuildInfo buildInfo) {
    info.put("app", appInfo.getInfo());
    info.put("git", gitInfo.getInfo());
    info.put("build", buildInfo.getInfo());
  }

  public Map<String, Object> getInfo() {
    return info;
  }
}
