package org.example.inpostpointradar.client.dto;

public record LocationDto(Double latitude, Double longitude) {
    public String toReadableString() {
        return String.format("%.6f, %.6f", latitude, longitude);
    }
}