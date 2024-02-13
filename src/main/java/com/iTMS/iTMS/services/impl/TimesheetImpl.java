package com.iTMS.iTMS.services.impl;

import com.iTMS.iTMS.dto.TaskId;
import com.iTMS.iTMS.dto.TimesheetsDTO;
import com.iTMS.iTMS.repositories.OracleTaskRepository;
import com.iTMS.iTMS.services.Timesheet;
import com.iTMS.iTMS.utilities.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimesheetImpl implements Timesheet {
    @Autowired
    private OracleTaskRepository repository;
    @Autowired
    private Converters converter;

    @Override
    public List<TimesheetsDTO> getTimesheets(List<String> list) {
        List<TaskId> taskIdList = converter.convertStringToTaskId(list);
        List<TimesheetsDTO> timesheetsDTOList = new ArrayList<>();
        taskIdList.forEach(e -> timesheetsDTOList.addAll(repository.getTimesheets(e.getClient(), e.getClientId())));
        return timesheetsDTOList;
    }
}
