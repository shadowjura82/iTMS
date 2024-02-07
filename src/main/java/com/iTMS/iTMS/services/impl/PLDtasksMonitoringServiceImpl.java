package com.iTMS.iTMS.services.impl;

import com.iTMS.iTMS.myDB.models.PLDtasks;
import com.iTMS.iTMS.myDB.repositories.PLDtasksRepository;
import com.iTMS.iTMS.services.PLDtasksMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PLDtasksMonitoringServiceImpl implements PLDtasksMonitoringService {
    @Autowired
    private PLDtasksRepository repository;

    @Override
    public List<PLDtasks> setAllTasks(List<String> list) {
        List<String> dbListOfTasks = repository.findAll().stream()
                .map(PLDtasks::getTask)
                .toList();
        list = list.stream()
                .filter(e -> !dbListOfTasks.contains(e))
                .toList();
        return repository.saveAll(convertStringToPLDTasks(list));
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

    private List<PLDtasks> convertStringToPLDTasks(List<String> list) {
        return list.stream()
                .map(e -> new PLDtasks(e, false, false))
                .collect(Collectors.toList());
    }
}
