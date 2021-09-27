package com.mycompany.freegame.client;

import com.mycompany.freegame.model.dto.response.GameResponseDto;
import reactor.core.publisher.Mono;

import java.util.List;

@FunctionalInterface
public interface FreeGameApiClient {
    Mono<List<GameResponseDto>> execute();
}
