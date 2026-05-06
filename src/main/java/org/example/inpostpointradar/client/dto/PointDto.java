package org.example.inpostpointradar.client.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PointDto(
        String name,
        Set<String> type,
        String status,
        Set<String> functions,
        @JsonProperty("address_details")
        AddressDto addressDetails,
        LocationDto location
) {}