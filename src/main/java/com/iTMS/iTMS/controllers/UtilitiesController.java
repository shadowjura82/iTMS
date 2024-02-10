package com.iTMS.iTMS.controllers;

import com.iTMS.iTMS.dto.TaskId;
import com.iTMS.iTMS.utilities.CheckPLDtimeSpend;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "API для работы с утилитами")
@RequestMapping("/utilities")
public class UtilitiesController {
    @Autowired
    private CheckPLDtimeSpend checkPLDtimeSpend;

    @PostMapping("/check_pld_time_spend")
    @Operation(summary = "Среднее арифметическое время потраченое командой PLD на обработку задач",
            description = "Этот ендпоинт позволяет сравнить время потраченное на рефение задач без помощи и с помощью сапорта<br>" +
                    "Параметры:<br>" +
                    "<b>dateFrom</b> - Начальная дата в формате YYYY-MM-DD HH24:MI:SS<br>" +
                    "<b>dateTo</b> - Окончательная дата в формате YYYY-MM-DD HH24:MI:SS<br>" +
                    "<b>RequestBody</b> - Список тасок в формате TaskId объектов")
    public ResponseEntity<String> getTasks(@RequestParam(defaultValue = "2024-01-01 00:00:00") String dateFrom,
                                           @RequestParam(defaultValue = "2025-01-01 23:59:00") String dateTo,
                                           @RequestParam(required = false, defaultValue = "C") String status,
                                           @RequestBody List<TaskId> taskId) {
        return ResponseEntity.ok(checkPLDtimeSpend.getComparison(dateFrom, dateTo, status, taskId));
    }
}
