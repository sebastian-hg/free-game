package com.mycompany.freegame.service;

import com.mycompany.freegame.model.Game;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface FindByNameFreeGame {
    Mono<Game> execute(String name);
}
