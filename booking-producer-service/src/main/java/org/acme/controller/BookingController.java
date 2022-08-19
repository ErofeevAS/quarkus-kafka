package org.acme.controller;


import lombok.extern.slf4j.Slf4j;
import org.acme.KafkaProducer;
import org.acme.dto.BookingDTO;
import org.acme.rest.client.dto.User;
import org.acme.rest.client.UserRestService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.UUID;

import static java.util.Objects.isNull;

@Path("/api/v1/booking")
@Slf4j
public class BookingController {

    private static final Logger LOGGER = Logger.getLogger(BookingController.class);

    @Inject
    KafkaProducer kafkaProducer;

    @RestClient
    UserRestService userService;


    @DELETE
    @Path("/{uuid}")
    public void delete(@PathParam("uuid") String uuid) {
        LOGGER.debugf("uuid: {}", uuid);
        kafkaProducer.delete(UUID.fromString(uuid));
    }

    @POST
    @Path("/{userId}")
    public Response create(BookingDTO bookingDTO, @PathParam("userId") Long userId) {

        User user = userService.getById(userId);
        if(isNull(user)){
            throw new ResourceNotFoundException(MessageFormat.format("user with id={0} not found", userId));
        }
        log.info("get user from user-api: " + user);

        kafkaProducer.create(bookingDTO, userId);

        return Response.accepted().build();
    }

    @PATCH
    public Response update(BookingDTO bookingDTO) {
        kafkaProducer.update(bookingDTO);
        return Response.accepted("new booking is processing").build();
    }

}