package com.iTMS.iTMS.dto;

import lombok.Data;

@Data
public class TimesheetsDTO {
    private TaskId taskId;
    private String employee;
    private Double hours;

    public TimesheetsDTO(String client, String taskid, String employee, Double hours) {
        this.taskId = new TaskId(client, taskid);
        this.employee = employee;
        this.hours = hours;
    }
}