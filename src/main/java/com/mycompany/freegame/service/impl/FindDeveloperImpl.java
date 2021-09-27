package com.mycompany.freegame.service.impl;

import com.mycompany.freegame.model.Developer;
import com.mycompany.freegame.model.dto.response.GameResponseDto;
import com.mycompany.freegame.repository.DeveloperRepository;
import com.mycompany.freegame.service.FindDeveloper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
@Log4j2
public class FindDeveloperImpl implements FindDeveloper {
    private final DeveloperRepository developerRepository;

    @Override
    public Mono<Developer> execute(GameResponseDto gameResponseDto) {
        var newDeveloper = Developer.builder()
                .id(0L)
                .company(gameResponseDto.getPublisher())
                .department(gameResponseDto.getDeveloper())
                .build();
        var resultDeveloper = developerRepository.findByCompany(gameResponseDto.getPublisher())
                .orElse(newDeveloper);

        return Mono.just(resultDeveloper);
    }
}
