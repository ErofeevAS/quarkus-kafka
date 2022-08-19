package org.acme.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
@ToString
public class TripWaypointDTO {

    private final UUID id;
//    private final Long bookingId;
    private final Boolean lastStop;
    private final String locality;
    private final Double lat;
    private final Double lng;
    private final Instant tripWayPointTimestamp;
}