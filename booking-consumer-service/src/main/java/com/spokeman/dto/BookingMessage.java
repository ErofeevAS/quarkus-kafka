package com.spokeman.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingMessage {
    private final Operation operation;
    private final String uuid;
    private final Long userId;
    private final BookingDTO data;
}
