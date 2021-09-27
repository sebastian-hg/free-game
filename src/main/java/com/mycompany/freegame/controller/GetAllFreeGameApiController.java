package com.mycompany.freegame.controller;

import com.mycompany.freegame.client.impl.FreeGameApiClientImpl;
import com.mycompany.freegame.model.dto.response.GameResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/games/all")
@RestController
public class GetAllFreeGameApiController {
    private final FreeGameApiClientImpl freeGameApiClient;

    @GetMapping
    public Mono<List<GameResponseDto>> viewAll() {
        return freeGameApiClient.execute();
    }
}
