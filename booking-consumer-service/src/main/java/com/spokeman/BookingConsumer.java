package com.spokeman;

import com.spokeman.dto.BookingDTO;
import com.spokeman.dto.BookingMessage;
import com.spokeman.dto.InformEvent;
import com.spokeman.service.BookingService;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Slf4j
public class BookingConsumer {

    @Inject
    BookingService bookingService;


    @Incoming("booking-delete-in")
    @Outgoing("booking-processed")
    public Uni<InformEvent> delete(Record<String, BookingMessage> record) {
        String key = record.key();
        BookingMessage message = record.value();
        log.info(key);
        log.info(message.getOperation().name());
        log.info(message.getData().toString());
        return bookingService.delete(message.getData().getId());
    }

    @Incoming("booking-create-in")
    @Outgoing("booking-processed")
    public Uni<InformEvent> create(Record<String, BookingMessage> record) {
        String key = record.key();
        BookingMessage message = record.value();
        log.info(key);
        log.info(message.getOperation().name());
        log.info(message.getData().toString());
        BookingDTO dto = message.getData();
        Long userId = message.getUserId();

        return bookingService.create(dto, userId);
    }

    @Incoming("booking-update-in")
    @Outgoing("booking-processed")
    public Uni<InformEvent> update(Record<String, BookingMessage> record) {
        String key = record.key();
        BookingMessage message = record.value();
        log.info(key);
        log.info(message.getOperation().name());
        log.info(message.getData().toString());
        BookingDTO dto = message.getData();

        return bookingService.update(dto);
    }

}