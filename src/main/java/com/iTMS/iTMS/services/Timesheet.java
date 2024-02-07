package com.iTMS.iTMS.services;

import com.iTMS.iTMS.dto.TaskId;
import com.iTMS.iTMS.dto.TimesheetsDTO;

import java.util.List;

public interface Timesheet {
    List<TimesheetsDTO> getTimesheets(List<TaskId> taskIdList);
}
