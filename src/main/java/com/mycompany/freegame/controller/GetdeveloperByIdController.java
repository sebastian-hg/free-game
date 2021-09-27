package com.mycompany.freegame.controller;

import com.mycompany.freegame.model.Developer;
import com.mycompany.freegame.service.GetDeveloperByID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("/developer/byId")
@RestController
public class GetdeveloperByIdController {
    private final GetDeveloperByID getDeveloperByID;

    @GetMapping(value = "/{id}")
    public Mono<Developer> getDeveloperById(@PathVariable(value = "id") Long id) {
        return getDeveloperByID.execute(id);
    }
}
