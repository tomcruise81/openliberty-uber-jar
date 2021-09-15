package org.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import org.sample.info.AppInfo;
import org.sample.info.BuildInfo;
import org.sample.info.GitInfo;

public class StaticPropertiesFilesTest {
	@Test
	public void hasApplicationProperties() throws Exception {
		InputStream applicationPropertiesStream = this.getClass().getClassLoader().getResourceAsStream(AppInfo.FILENAME);
		assertNotNull(applicationPropertiesStream, "Missing " + AppInfo.FILENAME);

		Properties applicationProperties = new Properties();
		applicationProperties.load(applicationPropertiesStream);

		assertEquals("openliberty-uber-jar-war", applicationProperties.getProperty("info.app.name"));
	}

	@Test
	public void hasGitProperties() throws Exception {
		InputStream gitPropertiesStream = this.getClass().getClassLoader().getResourceAsStream(GitInfo.FILENAME);
		assertNotNull(gitPropertiesStream, "Missing " + GitInfo.FILENAME);

		Properties gitProperties = new Properties();
		gitProperties.load(gitPropertiesStream);

		for (String propertyName : new String[] {"git.commit.id", "git.commit.message.full"}) {
			assertNotNull(gitProperties.getProperty(propertyName), "Missing " + propertyName);
		}
	}

	@Test
	public void hasBuildProperties() throws Exception {
		InputStream buildPropertiesStream = this.getClass().getClassLoader().getResourceAsStream(BuildInfo.FILENAME);
		assertNotNull(buildPropertiesStream, "Missing " + BuildInfo.FILENAME);

		Properties buildProperties = new Properties();
		buildProperties.load(buildPropertiesStream);

		for (String propertyName : new String[] {"build.time", "build.name"}) {
			assertNotNull(buildProperties.getProperty(propertyName), "Missing " + propertyName);
		}
	}
}
