package com.mycompany.freegame.service;

import com.mycompany.freegame.model.Developer;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface GetDeveloperByID {
    Mono<Developer> execute(Long id);
}
