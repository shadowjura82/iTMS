package com.iTMS.iTMS.controllers;

import com.iTMS.iTMS.WebExlib.Message;
import com.iTMS.iTMS.WebExlib.Webhook;
import com.iTMS.iTMS.services.TstService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "API для iTMS бота")
public class  TstController {

    private final TstService tstService;

    public TstController(TstService tstService) {
        this.tstService = tstService;
    }

    @GetMapping
    @Operation(summary = "Это тестовый ендпоинт")
    public String test() {
        return tstService.getToken();
    }

    @PostMapping("/webhook")
    @Operation(summary = "Тестовый webhook")
    public void webhook(@RequestBody Webhook message) {
        System.out.println("Метод отработал");
    }
}
