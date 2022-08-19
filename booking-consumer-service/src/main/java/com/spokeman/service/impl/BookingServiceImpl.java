package com.spokeman.service.impl;


import com.spokeman.dto.BookingDTO;
import com.spokeman.dto.BookingMapper;
import com.spokeman.dto.InformEvent;
import com.spokeman.entity.Booking;
import com.spokeman.service.BookingService;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Inject
    BookingMapper mapper;

    @Override
    @ReactiveTransactional
    public Uni<InformEvent> create(BookingDTO bookingDTO, Long userId) {
        Booking booking = mapper.toDAO(bookingDTO);

        booking.getTripWayPoints().forEach(wp -> wp.setBooking(booking));
        booking.setUserId(userId);
        Uni<Booking> persisted = booking.persist();

        return persisted
                .onItem()
                .transform(b -> convert(b, userId,"CREATED"))
                .invoke(x -> log.info(x.toString()));
    }



    @Override
    @ReactiveTransactional
    public Uni<InformEvent> delete(UUID uuid) {

        Uni<Boolean> isDeleted = Booking.deleteById(uuid);

        return isDeleted.onItem().invoke(x -> log.info("booking was deleted:{} ", uuid))
                .onItem().transform(b->InformEvent.builder()
                        .bookingId(uuid)
                        .operation("DELETED")
                        .build());

    }

    @Override
    @ReactiveTransactional
    public Uni<InformEvent> update(BookingDTO bookingDTO) {
        UUID uuid = bookingDTO.getId();

        Uni<Booking> entity = Booking.findById(uuid);

        return entity.onItem().invoke(booking -> log.info("booking was found:{} ", uuid))
                .onItem().invoke(booking -> mapper.updateEntity(bookingDTO, booking))
                .onItem().invoke(booking -> booking.persist())
                .onItem().invoke(booking -> log.info("booking was updated:{} ", uuid))
                .onItem().transform(b -> InformEvent.builder()
                        .bookingId(uuid)
                        .operation("UPDATED")
                        .build());
    }

    private InformEvent convert(Booking booking, Long userId, String operation) {
        log.info("CONVERTING.....");
        log.info(booking.toString());
        return InformEvent.builder()
                .bookingId(booking.id)
                .userId(userId)
                .operation(operation)
                .build();
    }

}
