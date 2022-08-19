package org.acme.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class BookingDTO implements Serializable {
    private final UUID id;
    private final String passengerName;
    private final String userId;
    private final String passengerContactNumber;
    private final OffsetDateTime pickupTime;
    private final Boolean asap = true;
    private final Integer waitingTime;
    private final Integer noOfPassengers;
    private final BigDecimal price;
    private final Integer rating;
    private final Instant createdOn;
    private final Instant lastModifiedOn;
    private final List<TripWaypointDTO> tripWayPoints;

}
