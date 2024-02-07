package com.iTMS.iTMS.controllers;

import com.iTMS.iTMS.dto.TaskId;
import com.iTMS.iTMS.dto.TimesheetsDTO;
import com.iTMS.iTMS.services.Timesheet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheets")
@Tag(name = "API для работы с таймшитами")
public class TimesheetController {
    @Autowired
    private Timesheet timesheetService;

    @PostMapping
    @Operation(summary = "Получить список тасок с таймшитами сотрудников",
            description = "Этот ендпоинт позволяет получить список тасок с таймшитами сотрудников<br>" +
                    "Входящий список тасок должен быть предоставлен в виде массива JSON объектов TaskId. Пример:<br>" +
                    "<b>{</b><br>" +
                    "<b>  \"client\": \"MAYO\",</b><br>" +
                    "<b>  \"clientId\": \"23479\"</b><br>" +
                    "<b>}</b>")
    public ResponseEntity<List<TimesheetsDTO>> getTimesheets(@RequestBody List<TaskId> taskIdList) {
        return ResponseEntity.ok(timesheetService.getTimesheets(taskIdList));
    }
}
