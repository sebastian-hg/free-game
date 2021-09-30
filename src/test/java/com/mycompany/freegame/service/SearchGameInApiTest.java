package com.mycompany.freegame.service;

import com.mycompany.freegame.client.FreeGameApiClient;
import com.mycompany.freegame.model.Developer;
import com.mycompany.freegame.model.Game;
import com.mycompany.freegame.model.dto.response.GameResponseDto;
import com.mycompany.freegame.repository.GameRepository;
import com.mycompany.freegame.service.impl.SearchGameInApiImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class SearchGameInApiTest {

    @Mock
    private FreeGameApiClient freeGameApiClient;
    @Mock
    private FindDeveloper findDeveloper;
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private SearchGameInApiImpl searchGameInApi;
    private Long idRequest;
    private GameResponseDto gameResponseDto;
    private final List<GameResponseDto> gameResponseDtoList = new ArrayList<>();
    private Game game;
    private Game gameExpected;
    private Mono<Game> gameResponse;

    @Test
    void givenRequestWhenExecuteThemOk() {
        givenRequest();
        givenFreeGameApiClient();
        givenFindDeveloper();
        givenGameRepository();
        givenResponse();
        whenExecute();
        themOK();
    }

    private void givenRequest() {
        idRequest = 1L;
    }

    private void givenFreeGameApiClient() {
        gameResponseDto = GameResponseDto.builder()
                .id(1L)
                .title("cars")
                .build();
        gameResponseDtoList.add(gameResponseDto);
        Mockito.when(freeGameApiClient.execute()).thenReturn(Mono.just(gameResponseDtoList));
    }

    private void givenFindDeveloper() {
        gameResponseDto = GameResponseDto.builder()
                .id(1L)
                .title("cars")
                .build();
        Developer developer = Developer.builder()
                .id(1L)
                .company("sega")
                .build();
        Mockito.when(findDeveloper.execute(gameResponseDto)).thenReturn(Mono.just(developer));
    }

    private void givenGameRepository() {
        game = Game.builder()
                .id(1L)
                .title("cars")
                .build();
        Mockito.when(gameRepository.save(game)).thenReturn(game);
    }

    private void givenResponse() {
        gameExpected = Game.builder()
                .id(1L)
                .title("cars")
                .build();
    }

    private void whenExecute() {
        gameResponse = searchGameInApi.execute(idRequest);
    }

    private void themOK() {
        StepVerifier.create(gameResponse)
                .expectNextMatches(game1 -> game1.equals(gameExpected))
                .expectComplete()
                .verify();
        Mockito.verify(freeGameApiClient).execute();
        Mockito.verify(findDeveloper).execute(gameResponseDto);
        Mockito.verify(gameRepository).save(game);
    }
}
