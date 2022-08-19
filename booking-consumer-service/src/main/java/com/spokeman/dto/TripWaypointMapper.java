package com.spokeman.dto;


import com.spokeman.entity.TripWaypoint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TripWaypointMapper {

    TripWaypointMapper INSTANCE = Mappers.getMapper(TripWaypointMapper.class);

    TripWaypointDTO convert(TripWaypoint entity);

}
