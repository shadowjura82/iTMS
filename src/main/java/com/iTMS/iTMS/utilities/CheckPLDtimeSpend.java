package com.iTMS.iTMS.utilities;

import com.iTMS.iTMS.configurations.Constants;
import com.iTMS.iTMS.dto.TimesheetsDTO;
import com.iTMS.iTMS.myDB.models.PLDtasks;
import com.iTMS.iTMS.myDB.repositories.PLDtasksRepository;
import com.iTMS.iTMS.repositories.OracleTaskRepository;
import com.iTMS.iTMS.services.Timesheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
@Slf4j
public class CheckPLDtimeSpend {
    @Autowired
    private OracleTaskRepository oracleTaskRepository;
    @Autowired
    private Converters converter;
    @Autowired
    private Environment env;
    @Autowired
    private Timesheet timesheet;
    @Autowired
    private PLDtasksRepository plDtasksRepository;
    @Autowired
    private Constants constants;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public String getComparison() {
        StringBuilder response = new StringBuilder();

        log.info("Получаем список тасок исследованых сапортом");
        List<PLDtasks> pldTasks = plDtasksRepository.findTaskByifTaskIsProcessedByPLDAndStatus(true, "C");
        List<String> supportTasks = converter.convertPLDtasksToString(pldTasks);

        log.info("Фильтруем таски которые решены командой PLD и закрыты");
        List<TimesheetsDTO> timesheets = getPLDprocessedTasks(supportTasks);

        log.info("Высчитываем среднее значение потраченного времени");
        Double avg = avgTimesheet(timesheets);
        response.append("Среднее время потраченное командой PLD на таски без помощи сапорта - ")
                .append(decimalFormat.format(avg))
                .append("\n");

        log.info("Высчитываем среднее значение потраченного времени");
        List<TimesheetsDTO> supportTimesheets = timesheet.getTimesheets(supportTasks);
        supportTimesheets = supportTimesheets.stream()
                .filter(e -> constants.PLD_EMPLOYEES.contains(e.getEmployee()))
                .toList();
        avg = avgTimesheet(supportTimesheets);
        response.append("Среднее время потраченное командой PLD на таски c помощю сапорта - ")
                .append(decimalFormat.format(avg));

        log.info("Ответ сформирован");
        return response.toString();
    }

    private List<TimesheetsDTO> getPLDprocessedTasks(List<String> supportTasks) {
        List<TimesheetsDTO> listOfPLDProcessedTasks = oracleTaskRepository.getTaskTimesheetPLD(
                env.getProperty("variable.bin"),
                env.getProperty("variable.dateFrom"),
                env.getProperty("variable.dateTo"),
                env.getProperty("variable.status"));
        return listOfPLDProcessedTasks.stream()
                .filter(e -> {
                    String task = e.getTaskId().getClient() + "-" + e.getTaskId().getClientId();
                    return !supportTasks.contains(task);
                })
                .toList();
    }

    private Double avgTimesheet(List<TimesheetsDTO> timesheets) {
        return timesheets.stream()
                .mapToDouble(TimesheetsDTO::getHours)
                .average().orElse(0.0);
    }
}
