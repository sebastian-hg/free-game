package com.mycompany.freegame.client.impl;

import com.mycompany.freegame.client.FreeGameApiClient;
import com.mycompany.freegame.exception.ErrorInCallToApiException;
import com.mycompany.freegame.model.dto.response.GameResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@Component
public class FreeGameApiClientImpl implements FreeGameApiClient {
    @Override
    public Mono<List<GameResponseDto>> execute() {
        return WebClient.create()
                .get()
                .uri("https://www.freetogame.com/api/games")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(GameResponseDto.class)
                .collectList()
                .onErrorMap(e -> new ErrorInCallToApiException("Error during call in API" + e.getMessage()));
    }
}
