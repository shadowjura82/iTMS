package com.iTMS.iTMS.services;

import com.iTMS.iTMS.repositories.OracleTaskRepository;
import com.iTMS.iTMS.myDB.models.PostgreSQLTask;
import com.iTMS.iTMS.myDB.repositories.PostgreSQLTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TstService {
    @Autowired
    private OracleTaskRepository oracleTaskRepository;
    @Autowired
    private PostgreSQLTaskRepository postgreSQLTaskRepository;

    public List<String> getTasks() {
        return oracleTaskRepository.getTasksAssignedToSupport();
    }

    public String transferAllTasks() {
        List<PostgreSQLTask> list = getTasks().stream()
                .map(e -> {
                    String[] task = e.split("-");
                    return new PostgreSQLTask(task[0], task[1]);
                }).toList();
        if (!postgreSQLTaskRepository.saveAll(list).isEmpty()) {
            return "<h1>Операция завершилась успешно!</h1>";
        } else {
            return "<h1>Что-то пошло не так!</h1>";
        }
    }
}
