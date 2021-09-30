package com.mycompany.freegame.service;

import com.mycompany.freegame.model.Game;
import com.mycompany.freegame.repository.GameRepository;
import com.mycompany.freegame.service.impl.GetFreeGameByIdImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class GetFreeGameByIdTest {
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GetFreeGameByIdImpl service;
    private Game gameExpected;
    private Mono<Game> monoResponse;
    private Long idRequest;

    @Test
    void givenARequestWhenGetFreeGameByIdThemIsOk() {
        givenARequest();
        givenAGameRepository();
        givenResponse();
        whenExecute();
        themIsAOk();
    }

    private void givenARequest() {
        idRequest = 1L;
    }

    private void givenAGameRepository() {
        Game gameTest = Game.builder()
                .id(1L)
                .title("fifa 22")
                .platform("console")
                .build();
        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.ofNullable(gameTest));
    }

    private void givenResponse() {
        gameExpected = Game.builder()
                .id(1L)
                .title("fifa 22")
                .platform("console")
                .build();
    }

    private void whenExecute() {
        monoResponse = service.execute(idRequest);
    }

    private void themIsAOk() {
        StepVerifier.create(monoResponse)
                .expectNextMatches(game -> game.equals(gameExpected))
                .expectComplete()
                .verify();
        Mockito.verify(gameRepository).findById(idRequest);
    }

    @Test
    void givenRequestWhenExecuteThemGameNoExist() {
        givenARequest();
        givenGameRepositoryNoExist();
        whenExecute();
        themNoExistGame();

    }

    private void givenGameRepositoryNoExist() {
        Mockito.when(gameRepository.findById(idRequest)).thenReturn(Optional.empty());
    }

    private void themNoExistGame() {
        StepVerifier.create(monoResponse)
                .expectComplete()
                .verify();
        Mockito.verify(gameRepository).findById(idRequest);
    }
}
