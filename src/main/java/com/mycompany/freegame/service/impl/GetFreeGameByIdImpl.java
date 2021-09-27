package com.mycompany.freegame.service.impl;

import com.mycompany.freegame.model.Game;
import com.mycompany.freegame.repository.GameRepository;
import com.mycompany.freegame.service.GetFreeGameById;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
@Log4j2
public class GetFreeGameByIdImpl implements GetFreeGameById {

    private final GameRepository gameRepository;

    @Override
    public Mono<Game> execute(Long id) {
        var gameRepositoryResult = gameRepository.findById(id);
        return gameRepositoryResult.map(Mono::just).orElseGet(Mono::empty);
    }

}
