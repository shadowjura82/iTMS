package com.iTMS.iTMS.services.impl;

import com.iTMS.iTMS.dto.TaskId;
import com.iTMS.iTMS.repositories.OracleTaskRepository;
import com.iTMS.iTMS.services.GeneralOperationsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralOperationsServiceImpl implements GeneralOperationsService {
    private final OracleTaskRepository oracleTaskRepository;

    public GeneralOperationsServiceImpl(OracleTaskRepository oracleTaskRepository) {
        this.oracleTaskRepository = oracleTaskRepository;
    }

    public List<TaskId> getListOfTasksProcessedByEmployee(String bin, String dateFrom, String dateTo, String status) {
        if (status == null || status.isEmpty() || status.isBlank()) status = "C";
        return oracleTaskRepository.getListOfTasksProcessedByEmployee(bin, dateFrom, dateTo, status);
    }
}