package com.mycompany.freegame.controller;

import com.mycompany.freegame.model.Game;
import com.mycompany.freegame.service.GetFreeGameById;
import com.mycompany.freegame.service.SearchGameInApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("/games/byId")
@RestController
public class GetFreeGameByIdController {
    private final GetFreeGameById getFreeGameById;
    private final SearchGameInApi searchGameInApi;

    @GetMapping(value = "/{id}")
    public Mono<Game> getGameById(@PathVariable(value = "id") Long id) {
        return getFreeGameById.execute(id).switchIfEmpty(searchGameInApi.execute(id));
    }
}
