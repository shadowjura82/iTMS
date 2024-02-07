package com.iTMS.iTMS.controllers;

import com.iTMS.iTMS.myDB.models.PLDtasks;
import com.iTMS.iTMS.services.PLDtasksMonitoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "API для мониторинга PLD тасок")
@RequestMapping("/pld")
public class PLDTasksController {
    @Autowired
    private PLDtasksMonitoringService service;

    @PostMapping("/fill")
    @Operation(summary = "Обновить список тасок которые нам предоставила команда PLD",
            description = "Этот ендпоинт позволяет добавить новые таски в общий список<br>" +
                    "Список должен быть предоставлен в виде массива JSON стрингов. Пример:<br>" +
                    "<b>[</b>" +
                    "<b>  \"FCS-07118\",</b>" +
                    "<b>  \"MTSIN-19135\",</b>" +
                    "<b>  \"ARCMR-02080\"</b>" +
                    "<b>]</b>")
    private ResponseEntity<List<PLDtasks>> fillAllTasks(@RequestBody List<String> list) {
        return ResponseEntity.ok(service.setAllTasks(list));
    }

    @PostMapping("/support_proc")
    @Operation(summary = "Обновить список тасок которые были решены с помощью саппорта",
            description = "Этот ендпоинт позволяет отметить таски которые были решены сапортом<br>" +
                    "Список должен быть предоставлен в виде массива JSON стрингов. Пример:<br>" +
                    "<b>[</b>" +
                    "<b>  \"FCS-07118\",</b>" +
                    "<b>  \"MTSIN-19135\",</b>" +
                    "<b>  \"ARCMR-02080\"</b>" +
                    "<b>]</b>")
    private ResponseEntity<List<PLDtasks>> markSupportSolvedTasks(@RequestBody List<String> list) {
        return ResponseEntity.ok(service.markSolvedTasks(list, true));
    }

    @PostMapping("/pld_proc")
    @Operation(summary = "Обновить список тасок которые были решены с помощью команды PLD",
            description = "Этот ендпоинт позволяет отметить таски которые были решены командой PLD<br>" +
                    "Список должен быть предоставлен в виде массива JSON стрингов. Пример:<br>" +
                    "<b>[</b>" +
                    "<b>  \"FCS-07118\",</b>" +
                    "<b>  \"MTSIN-19135\",</b>" +
                    "<b>  \"ARCMR-02080\"</b>" +
                    "<b>]</b>")
    private ResponseEntity<List<PLDtasks>> markPLDSolvedTasks(@RequestBody List<String> list) {
        return ResponseEntity.ok(service.markSolvedTasks(list, false));
    }
}