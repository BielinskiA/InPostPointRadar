package org.example.inpostpointradar.client.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record InPostResponse(
        List<PointDto> items,
        @JsonProperty("total_pages")
        int totalPages,
        int count
) {}