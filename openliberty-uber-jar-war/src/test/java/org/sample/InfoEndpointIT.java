package org.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.provider.jsrjsonp.JsrJsonpProvider;
import org.junit.jupiter.api.Test;

public class InfoEndpointIT {
  @Test
  public void testGetProperties() {
      String serviceHostUrl = System.getenv("SERVICE_HOST_URL");
      assertNotNull(serviceHostUrl);

      String contextRoot = System.getProperty("context.root", "");
      String url = serviceHostUrl + contextRoot;

      // Trim trailing slash
      url = (url.endsWith("/")) ? url.substring(0, url.length() - 1) : url;

      Client client = ClientBuilder.newClient();
      client.register(JsrJsonpProvider.class);

      String targetURIString = url + "/system/info";
      WebTarget target = client.target(targetURIString);
      Response response = target.request().get();

      assertEquals(200, response.getStatus(), "Incorrect response code from " + url);

      response.readEntity(JsonObject.class);
      response.close();
  }
}
