package org.example.inpostpointradar.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InPostResponse {
    @JsonProperty("items")
    private List<PointDto> items;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("count")
    private int count;
}