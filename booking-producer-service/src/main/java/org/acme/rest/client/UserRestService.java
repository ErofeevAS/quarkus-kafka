package org.acme.rest.client;

import org.acme.rest.client.dto.User;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;

@Path("/api/v1/users/")
@RegisterRestClient(configKey = "users-api")
public interface UserRestService {
    @GET
    @Path("{id}")
//    @Produces
    User getById(@PathParam ("id") Long id);
}
