package org.example.inpostpointradar.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointDto {
    private String name;
    @JsonProperty("type")
    private Set<String> type;
    private String status;
    @JsonProperty("functions")
    private Set<String> functions;
    @JsonProperty("address_details")
    private AddressDto addressDetails;
    @JsonProperty("location")
    private LocationDto location;
}