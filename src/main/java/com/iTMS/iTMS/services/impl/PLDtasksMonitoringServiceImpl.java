package com.iTMS.iTMS.services.impl;

import com.iTMS.iTMS.myDB.models.PLDtasks;
import com.iTMS.iTMS.myDB.repositories.PLDtasksRepository;
import com.iTMS.iTMS.repositories.OracleTaskRepository;
import com.iTMS.iTMS.services.PLDtasksMonitoringService;
import com.iTMS.iTMS.utilities.Converters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PLDtasksMonitoringServiceImpl implements PLDtasksMonitoringService {
    @Autowired
    private PLDtasksRepository repository;
    @Autowired
    private OracleTaskRepository oracleRepository;
    @Autowired
    private Converters converter;

    @Override
    public List<PLDtasks> setAllTasks(List<String> list) {
        List<PLDtasks> listToUpdate = list.stream()
                .map(e -> new PLDtasks(e, false, false, ""))
                .filter(e -> {
                    List<String> toCompare = repository.findAll().stream()
                            .map(PLDtasks::getTask)
                            .toList();
                    return !toCompare.contains(e.getTask());
                })
                .peek(e -> {
                    String[] params = e.getTask().split("-");
                    e.setStatus(oracleRepository.getTaskStatus(params[0], params[1]));
                })
                .toList();
        return repository.saveAll(listToUpdate);
    }

    @Override
    public List<PLDtasks> markSolvedTasks(List<String> list, boolean isSupport) {
        List<PLDtasks> dbList = repository.findAll();
        dbList.forEach(e -> {
            if (list.contains(e.getTask())) {
                if (isSupport) {
                    e.setIfTaskIsProcessedBySupport(true);
                } else {
                    e.setIfTaskIsProcessedByPLD(true);
                }
            }
        });
        return repository.saveAll(dbList);
    }

    public String updateTaskStatus(List<String> list) {
        List<PLDtasks> dbList = repository.findAll();
        dbList = dbList.stream()
                .filter(task -> list.contains(task.getTask()))
                .peek(task -> {
                    String[] arg = task.getTask().split("-");
                    task.setStatus(oracleRepository.getTaskStatus(arg[0], arg[1]));
                })
                .toList();
        repository.saveAll(dbList);
        return "Статусы были обновлены для таких тасок:\n" + dbList.stream()
                .map(PLDtasks::taskToString)
                .toList();
    }
}
