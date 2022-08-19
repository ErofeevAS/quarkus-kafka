package org.acme.serializer;


import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import org.acme.dto.BookingMessage;

public class BookingDeserializer extends ObjectMapperDeserializer<BookingMessage> {

    public BookingDeserializer() {
        super(BookingMessage.class);
    }
}
