package org.example.inpostpointradar.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {
    private String city;
    private String province;
    @JsonProperty("post_code")
    private String postCode;
    private String street;
    @JsonProperty("building_number")
    private String buildingNumber;
    private String line1;
    private String line2;
}