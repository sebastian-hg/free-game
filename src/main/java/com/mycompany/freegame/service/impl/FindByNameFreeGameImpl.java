package com.mycompany.freegame.service.impl;

import com.mycompany.freegame.model.Game;
import com.mycompany.freegame.repository.GameRepository;
import com.mycompany.freegame.service.FindByNameFreeGame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class FindByNameFreeGameImpl implements FindByNameFreeGame {
    private final GameRepository repositoryJpa;
    @Override
    public Mono<Game> execute(String name) {

        return null;
    }
}
