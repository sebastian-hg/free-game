package com.mycompany.freegame.service;

import com.mycompany.freegame.model.Developer;
import com.mycompany.freegame.model.dto.response.GameResponseDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface FindDeveloper {
    Mono<Developer> execute(GameResponseDto gameResponseDto);
}

