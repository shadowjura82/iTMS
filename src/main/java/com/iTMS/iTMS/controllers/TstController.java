package com.iTMS.iTMS.controllers;

import com.iTMS.iTMS.services.TstService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TstController {

    private final TstService tstService;

    public TstController(TstService tstService) {
        this.tstService = tstService;
    }

    @GetMapping
    public String test() {
        return "Test message";
    }
}
