package com.mycompany.freegame.service.impl;

import com.mycompany.freegame.model.Developer;
import com.mycompany.freegame.repository.DeveloperRepository;
import com.mycompany.freegame.service.GetDeveloperByID;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
@Log4j2
public class GetDeveloperByIDImpl implements GetDeveloperByID {
    private final DeveloperRepository repository;
    @Override
    public Mono<Developer> execute(Long id) {
        var developer= repository.findById(id);
        return Mono.just(developer.get());
    }
}
