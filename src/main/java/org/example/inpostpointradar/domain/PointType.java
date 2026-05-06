package org.example.inpostpointradar.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PointType {
    PARCEL_LOCKER("parcel_locker"),
    PICKUP_POINT("pickup_point"),
    PARCEL_STATION("parcel_station"),
    UNKNOWN("unknown");

    private final String value;

    public static PointType fromString(String text) {
        return java.util.Arrays.stream(PointType.values())
                .filter(type -> type.value.equalsIgnoreCase(text))
                .findFirst()
                .orElse(UNKNOWN);
    }
}