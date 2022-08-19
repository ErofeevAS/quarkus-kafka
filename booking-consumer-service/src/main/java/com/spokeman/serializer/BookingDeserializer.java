package com.spokeman.serializer;


import com.spokeman.dto.BookingMessage;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class BookingDeserializer extends ObjectMapperDeserializer<BookingMessage> {

    public BookingDeserializer() {
        super(BookingMessage.class);
    }
}
