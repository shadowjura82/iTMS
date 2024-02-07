package com.iTMS.iTMS.services;

import com.iTMS.iTMS.dto.TaskId;

import java.util.List;

public interface GeneralOperationsService {
    List<TaskId> getListOfTasksProcessedByEmployee(String bin, String dateFrom, String dateTo, String status);
}
