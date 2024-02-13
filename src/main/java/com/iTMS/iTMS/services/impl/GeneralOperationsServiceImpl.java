package com.iTMS.iTMS.services.impl;

import com.iTMS.iTMS.dto.TaskId;
import com.iTMS.iTMS.repositories.OracleTaskRepository;
import com.iTMS.iTMS.services.GeneralOperationsService;
import com.iTMS.iTMS.utilities.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralOperationsServiceImpl implements GeneralOperationsService {
    private final OracleTaskRepository oracleTaskRepository;
    @Autowired
    private Converters converter;

    public GeneralOperationsServiceImpl(OracleTaskRepository oracleTaskRepository) {
        this.oracleTaskRepository = oracleTaskRepository;
    }

    public List<String> getListOfTasksProcessedByEmployee(String bin, String dateFrom, String dateTo, String status) {
        if (status == null || status.isEmpty() || status.isBlank()) status = "C";
        List<TaskId> result = oracleTaskRepository.getListOfTasksProcessedByEmployee(bin, dateFrom, dateTo, status);
        return converter.convertTaskIdToString(result);
    }
}