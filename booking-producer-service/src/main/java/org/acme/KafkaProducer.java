package org.acme;

import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import org.acme.dto.BookingDTO;
import org.acme.dto.BookingMessage;
import org.acme.dto.Operation;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class KafkaProducer {

    @Channel("booking-delete")
    Emitter<BookingMessage> bookingDeleteEmitter;

    @Channel("booking-create")
    Emitter<BookingMessage> bookingAddEmitter;

    @Channel("booking-update")
    Emitter<BookingMessage> bookingUpdateEmitter;

    public void delete(UUID uuid) {
        BookingDTO booking = BookingDTO.builder()
                .id(uuid).build();

        BookingMessage data = BookingMessage.builder()
                .operation(Operation.DELETE)
                .data(booking).build();

        Message<BookingMessage> message = Message.of(data).addMetadata(generateMetadata());

        bookingDeleteEmitter.send(message);
    }

    public void create(BookingDTO bookingDTO, Long userId) {
        BookingMessage data = BookingMessage.builder()
                .operation(Operation.CREATE)
                .userId(userId)
                .data(bookingDTO).build();

        Message<BookingMessage> message = Message.of(data).addMetadata(generateMetadata());

        bookingAddEmitter.send(message);
    }

    public void update(BookingDTO bookingDTO) {
        BookingMessage data = BookingMessage.builder()
                .operation(Operation.UPDATE)
                .data(bookingDTO).build();

        Message<BookingMessage> message = Message.of(data).addMetadata(generateMetadata());
        bookingUpdateEmitter.send(message);
    }

    private OutgoingKafkaRecordMetadata generateMetadata() {

        return OutgoingKafkaRecordMetadata.builder()
                .withKey(UUID.randomUUID().toString())
                .build();
    }
}
