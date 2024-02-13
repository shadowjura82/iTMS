package com.iTMS.iTMS.controllers;

import com.iTMS.iTMS.utilities.CheckPLDtimeSpend;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "API для работы с утилитами")
@RequestMapping("/utilities")
public class UtilitiesController {
    @Autowired
    private CheckPLDtimeSpend checkPLDtimeSpend;

    @GetMapping("/check_pld_time_spend")
    @Operation(summary = "Среднее арифметическое время потраченое командой PLD на обработку задач",
            description = "Этот ендпоинт позволяет сравнить время потраченное на решение задач без помощи и с помощью сапорта<br>" +
                    "Таски решенные без помощи сапорта вытягиваются с базы данных. Параметры запроса в application.properties" +
                    "Таски решенные c помощью сапорта вытягиваются с локальной базы данных.")
    public ResponseEntity<String> getTasks() {
        return ResponseEntity.ok(checkPLDtimeSpend.getComparison());
    }
}
