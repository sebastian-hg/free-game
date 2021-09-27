package com.mycompany.freegame.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GameResponseDto {
    Long id;
    String title;
    String thumbnail;
    String shortDescription;
    String gameUrl;
    String genre;
    String platform;
    String publisher;
    String developer;
    String releaseDate;
    String freeToGameProfileUrl;


}
