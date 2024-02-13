package com.iTMS.iTMS.controllers;

import com.iTMS.iTMS.services.impl.GeneralOperationsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "API для общих операций с тасками")
@RequestMapping("/task")
public class GeneralController {
    @Autowired
    private GeneralOperationsServiceImpl service;

    @GetMapping("/processed_by")
    @Operation(summary = "Список тасок с которыми работал сотрудник или комманда",
            description = "Этот ендпоинт позволяет получить список задач в котором принимал участие сотрудник<br>" +
                    "Параметры:<br>" +
                    "<b>bin</b> - iTMS бин сотрудника<br>" +
                    "<b>dateFrom</b> - Начальная дата в формате YYYY-MM-DD HH24:MI:SS<br>" +
                    "<b>dateTo</b> - Окончательная дата в формате YYYY-MM-DD HH24:MI:SS<br>" +
                    "<b>status</b> - Статус тасок. По умолчанию параметр равен С (закрытые таски)")
    public ResponseEntity<List<String>> getTasks(@RequestParam(defaultValue = "N/GENESPL") String bin,
                                                 @RequestParam(defaultValue = "2024-01-01 00:00:00") String dateFrom,
                                                 @RequestParam(defaultValue = "2025-01-01 23:59:00") String dateTo,
                                                 @RequestParam(required = false, defaultValue = "C") String status) {
        return ResponseEntity.ok(service.getListOfTasksProcessedByEmployee(bin, dateFrom, dateTo, status));
    }
}
