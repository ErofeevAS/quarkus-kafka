package com.spokeman.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
@ToString
public class InformEvent implements Serializable {
    private final UUID bookingId;
    private final Long userId;
    private final String operation;
}
