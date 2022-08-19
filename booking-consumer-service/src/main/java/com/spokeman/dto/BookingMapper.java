package com.spokeman.dto;

import com.spokeman.entity.Booking;
import org.mapstruct.*;

@Mapper(componentModel = "cdi")
//@Mapper(uses = {TripWaypointMapper.class})
public interface BookingMapper {

    BookingDTO toDTO(Booking booking);

    @Mapping(target = "super.id", ignore = true)
    Booking toDAO(BookingDTO bookingDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(BookingDTO dto, @MappingTarget Booking entity);

}
