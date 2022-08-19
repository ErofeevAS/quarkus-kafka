package com.spokeman.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Cacheable
public class Booking extends UUIDAbstractEntity implements Serializable {

    @Column
    private String passengerName;
    @Column
    private String passengerContactNumber;
    @Column
    private OffsetDateTime pickupTime;
    @Column
    private Boolean asap = true;
    @Column
    private Integer waitingTime;
    @Column
    private Integer noOfPassengers;
    @Column
    private BigDecimal price;
    @Column
    private Integer rating;
    @Column
    private Instant createdOn;
    @Column
    private Instant lastModifiedOn;

    @JsonIgnoreProperties
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column
    private List<TripWaypoint> tripWayPoints = new ArrayList<>();

    private Long userId;
}
