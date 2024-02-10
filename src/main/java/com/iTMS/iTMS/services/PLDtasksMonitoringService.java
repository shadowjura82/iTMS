package com.iTMS.iTMS.services;

import com.iTMS.iTMS.myDB.models.PLDtasks;

import java.util.List;

public interface PLDtasksMonitoringService {
    List<PLDtasks> setAllTasks(List<String> list);

    List<PLDtasks> markSolvedTasks(List<String> list, boolean isSupport);

    String updateTaskStatus(List<String> list);
}
