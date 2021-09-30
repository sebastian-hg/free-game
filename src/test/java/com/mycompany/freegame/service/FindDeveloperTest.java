package com.mycompany.freegame.service;

import com.mycompany.freegame.model.Developer;
import com.mycompany.freegame.model.dto.response.GameResponseDto;
import com.mycompany.freegame.repository.DeveloperRepository;
import com.mycompany.freegame.service.impl.FindDeveloperImpl;
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
public class FindDeveloperTest {

    @Mock
    private DeveloperRepository repository;

    @InjectMocks
    private FindDeveloperImpl findDeveloper;
    private GameResponseDto gameResponseDto;
    private Developer developerExpected;
    private Mono<Developer> developerResponse;


    @Test
    void givenRequestWhenExecuteThemOk() {
        givenRequest();
        givenRepository();
        givenResponse();
        whenExecute();
        themOk();
    }

    private void givenRequest() {
        gameResponseDto = GameResponseDto.builder()
                .id(1L)
                .title("mario Cars")
                .publisher("sega")
                .developer("sports")
                .build();
    }

    private void givenRepository() {
        Developer developer = Developer.builder()
                .id(1L)
                .company("sega")
                .build();
        Mockito.when(repository.findByCompany(gameResponseDto.getPublisher()))
                .thenReturn(Optional.ofNullable(developer));
    }

    private void givenResponse() {
        developerExpected = Developer.builder()
                .id(1L)
                .company("sega")
                .build();
    }

    private void whenExecute() {
        developerResponse = findDeveloper.execute(gameResponseDto);
    }

    private void themOk() {
        StepVerifier.create(developerResponse)
                .expectNextMatches(developer -> developer.equals(developerExpected))
                .expectComplete()
                .verify();
        Mockito.verify(repository).findByCompany(gameResponseDto.getPublisher());
    }

}
