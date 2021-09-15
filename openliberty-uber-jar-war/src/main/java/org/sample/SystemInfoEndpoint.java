package org.sample;

import javax.ws.rs.core.Response;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import org.sample.info.SystemInfo;

@RequestScoped
@Path("/info")
public class SystemInfoEndpoint {

	@Inject
	SystemInfo systemInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Timed(name = "getInfoTime", description = "Time needed to get the 'system' info")
	@Counted(absolute = true, description = "Number of times the 'system' info are requested")
	public Response getInfo() {
	    return Response.ok(systemInfo.getInfo()).build();
	}
}
