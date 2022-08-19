package com.spokeman.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@Cacheable
public class TripWaypoint extends UUIDAbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "fk_tripwaypoint_on_book"))
    private Booking booking;
    @Column
    private Boolean lastStop;
    @Column
    private String locality;
    @Column
    private Double lat;
    @Column
    private Double lng;
    @Column
    private Instant tripWayPointTimestamp;
}