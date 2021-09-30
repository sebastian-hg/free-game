package com.mycompany.freegame.service.impl;

import com.mycompany.freegame.client.FreeGameApiClient;
import com.mycompany.freegame.exception.GameNoExistsException;
import com.mycompany.freegame.model.Game;
import com.mycompany.freegame.model.dto.response.GameResponseDto;
import com.mycompany.freegame.repository.DeveloperRepository;
import com.mycompany.freegame.repository.GameRepository;
import com.mycompany.freegame.service.FindDeveloper;
import com.mycompany.freegame.service.SearchGameInApi;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@AllArgsConstructor
@Service
@Log4j2
public class SearchGameInApiImpl implements SearchGameInApi {
    private final FreeGameApiClient freeGameApiClient;
    private final FindDeveloper findDeveloper;
    private final GameRepository gameRepository;

    public Mono<Game> execute(Long id) {
        return freeGameApiClient.execute()
                .flatMap(gameResponseDtoList -> {
                    GameResponseDto gameResponseDtoResult = null;
                    for (GameResponseDto responseDto : gameResponseDtoList) {
                        if (Objects.equals(responseDto.getId(), id)) {
                            gameResponseDtoResult = responseDto;
                            break;
                        }
                    }
                    return (Objects.isNull(gameResponseDtoResult))
                            ? Mono.empty()
                            : Mono.just(gameResponseDtoResult);

                })
                .flatMap(gameResponseDto -> Mono.just(gameResponseDto).zipWith(findDeveloper.execute(gameResponseDto))
                )
                .map(tuple2 -> {
                    var gameResponseDto = tuple2.getT1();
                    return gameRepository.save(Game.builder()
                            .description(gameResponseDto.getShortDescription())
                            .platform(gameResponseDto.getPlatform())
                            .title(gameResponseDto.getTitle())
                            .category(gameResponseDto.getGenre())
                            .id(gameResponseDto.getId())
                            .build());
                })
                .switchIfEmpty(Mono.error(new GameNoExistsException("game no exist, try looking for another game")))
                ;
    }
}
