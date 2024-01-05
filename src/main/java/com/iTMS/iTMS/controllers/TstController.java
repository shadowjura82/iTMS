package com.iTMS.iTMS.controllers;

import com.iTMS.iTMS.services.TstService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "API для iTMS бота")
public class TstController {

    private final TstService tstService;

    public TstController(TstService tstService) {
        this.tstService = tstService;
    }

    @GetMapping
    @Operation(summary = "Это тестовый ендпоинт")
    public List<String> getTasks() {
        return tstService.getTasks();
    }

    @GetMapping("transfer")
    @Operation(summary = "Копирование тасок в локальную БД")
    public String transferAllTasks() {
        return tstService.transferAllTasks();
    }

    @PostMapping
    @Operation(summary = "Тестовый webhook")
    public void webhook(@RequestBody() byte[] file) {
        System.out.println("Метод отработал");
    }
}
