package com.mycompany.freegame.service;

import com.mycompany.freegame.client.FreeGameApiClient;
import com.mycompany.freegame.model.Developer;
import com.mycompany.freegame.model.Game;
import com.mycompany.freegame.model.dto.response.GameResponseDto;
import com.mycompany.freegame.repository.DeveloperRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class GetFreeGameByIdTest {
    @Mock
    private GameRepository gameRepository;
    @Mock
    private DeveloperRepository developerRepository;
    @Mock
    private FreeGameApiClient freeGameApiClient;

    @InjectMocks
    private GetFreeGameByIdImpl service;
    private Game gameExpected;
    private Mono<Game> monoResponse;
    private Long idRequest;
    private String nameRequest;
    private final List<GameResponseDto> gameResponseDtoList = new ArrayList<>();
    private Game gameSave;


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
        Developer developerTest = Developer.builder()
                .id(1L)
                .department("sport")
                .company("ea games")
                .build();
        Game gameTest = Game.builder()
                .id(1L)
                .title("fifa 22")
                .platform("console")
                .build();
        Mockito.when(gameRepository.findById(1L)).thenReturn(Optional.ofNullable(gameTest));
    }

    private void givenResponse() {
        var developerTest2 = Developer.builder()
                .id(1L)
                .department("sport")
                .company("ea games")
                .build();
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
    void givenARequestWhenGetFreeGameByiDThemIdNoExistInDataBase() {
        givenARequestThemIdNoExist();
        givenAGameRepositoryFalse();
        givenAFreeGameApiClient();
        givenAGameRepositorySave();
        givenAResponse();
        whenExecuteWithIdNoExist();
        themIdNoExistsInDataBase();
    }

    private void givenARequestThemIdNoExist() {
        idRequest = 1L;
        nameRequest = "ea games";
        Developer developer = Developer.builder()
                .id(1L)
                .company("ea games")
                .build();
        gameSave = Game.builder()
                .id(1L)
                .platform("mobile")
                .category("sport")
                .build();
    }

    private void givenAGameRepositoryFalse() {
        Mockito.when(gameRepository.findById(idRequest)).thenReturn(Optional.empty());
    }

    private void givenAFreeGameApiClient() {
        Developer developer = Developer.builder()
                .id(1L)
                .company("ea games")
                .build();
        GameResponseDto gameResponseDto = GameResponseDto.builder()
                .id(1L)
                .publisher("ea games")
                .platform("mobile")
                .genre("sport")
                .build();
        gameResponseDtoList.add(gameResponseDto);
        Mockito.when(developerRepository.findByCompany(nameRequest)).thenReturn(Optional.ofNullable(developer));
        Mockito.when(freeGameApiClient.execute()).thenReturn(Mono.just(gameResponseDtoList));
    }

    private void givenAGameRepositorySave() {
        Mockito.when(gameRepository.save(gameSave)).thenReturn(gameSave);
    }

    private void givenAResponse() {
        Developer developer = Developer.builder()
                .id(1L)
                .company("ea games")
                .build();
        gameExpected = Game.builder()
                .id(1L)
                .platform("mobile")
                .category("sport")
                .build();
    }

    private void whenExecuteWithIdNoExist() {
        monoResponse = service.execute(idRequest);
    }

    private void themIdNoExistsInDataBase() {
        StepVerifier.create(monoResponse)
                .expectNextMatches(game -> game.equals(gameExpected))
                .expectComplete()
                .verify();
        Mockito.verify(gameRepository).findById(idRequest);
        Mockito.verify(freeGameApiClient).execute();
        Mockito.verify(developerRepository).findByCompany(nameRequest);
        Mockito.verify(gameRepository).save(gameSave);
    }

    @Test
    void givenARequestWhenGetFreeGameByIdThemCreateNewDeveloper() {
        givenARequestThemIdNoExist();
        givenAGameRepository();
        givenAFreeGameApiClientThemCreateNewDeveloper();
        givenResponseNewDeveloper();
        whenExecuteCreateNewDeveloper();
        themCreateNewDeveloper();

    }

    private void givenAFreeGameApiClientThemCreateNewDeveloper() {
        Developer newDeveloper = Developer.builder()
                .id(1L)
                .company("ea games")
                .build();
        GameResponseDto gameResponseDto = GameResponseDto.builder()
                .id(1L)
                .publisher("ea games")
                .platform("mobile")
                .genre("sport")
                .build();
        gameResponseDtoList.add(gameResponseDto);
        Mockito.when(developerRepository.findByCompany(nameRequest)).thenReturn(Optional.empty())
                .thenReturn(Optional.of(newDeveloper));
        Mockito.when(freeGameApiClient.execute()).thenReturn(Mono.just(gameResponseDtoList));
    }

    private void givenResponseNewDeveloper() {

    }

    private void whenExecuteCreateNewDeveloper() {

    }

    private void themCreateNewDeveloper() {

    }

}

