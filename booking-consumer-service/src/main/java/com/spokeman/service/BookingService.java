package com.spokeman.service;

import com.spokeman.dto.BookingDTO;
import com.spokeman.dto.InformEvent;
import com.spokeman.entity.Booking;
import io.smallrye.mutiny.Uni;

import java.util.UUID;

public interface BookingService {

    Uni<InformEvent> create(BookingDTO bookingDTO, Long userId);

    Uni<InformEvent> delete(UUID uuid);

    Uni<InformEvent> update(BookingDTO bookingDTO);
}
