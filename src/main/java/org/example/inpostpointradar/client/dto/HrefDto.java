package org.example.inpostpointradar.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrefDto {

    private String href;

    @JsonCreator
    public static HrefDto fromString(String href) {
        return new HrefDto(href);
    }

    @JsonValue
    public String getHref() {
        return href;
    }
}