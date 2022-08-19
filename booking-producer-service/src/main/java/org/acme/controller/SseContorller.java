package org.acme.controller;

import io.smallrye.mutiny.Multi;
import org.acme.dto.InformEvent;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.reactive.RestStreamElementType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/sse")
public class SseContorller {

    @Inject
    @Channel("booking")
    Multi<InformEvent> booking;

    @GET
    @Path("/booking/{userId}")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.TEXT_PLAIN)
    public Multi<InformEvent> stream(@PathParam("userId") String userId) {
        System.out.println("123");
//        return booking.filter(x -> x > max);
//        return booking.filter(event -> userId.equals(event.getUserId()));
        return booking;
    }
}
