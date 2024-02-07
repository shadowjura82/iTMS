package com.iTMS.iTMS.services.impl;

import com.iTMS.iTMS.dto.TaskId;
import com.iTMS.iTMS.dto.TimesheetsDTO;
import com.iTMS.iTMS.repositories.OracleTaskRepository;
import com.iTMS.iTMS.services.Timesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimesheetImpl implements Timesheet {
    @Autowired
    private OracleTaskRepository repository;

    @Override
    public List<TimesheetsDTO> getTimesheets(List<TaskId> taskIdList) {
        List<TimesheetsDTO> timesheetsDTOList = new ArrayList<>();
        taskIdList.forEach(e -> timesheetsDTOList.addAll(repository.getTimesheets(e.getClient(), e.getClientId())));
        return timesheetsDTOList;
    }
}
