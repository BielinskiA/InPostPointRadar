package org.example.inpostpointradar.client.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressDto(
        String city,
        String province,
        @JsonProperty("post_code")
        String postCode,
        String street,
        @JsonProperty("building_number")
        String buildingNumber
) {}