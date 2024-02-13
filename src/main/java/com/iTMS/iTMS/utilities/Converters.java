package com.iTMS.iTMS.utilities;

import com.iTMS.iTMS.dto.TaskId;
import com.iTMS.iTMS.myDB.models.PLDtasks;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Converters {
    public List<TaskId> convertStringToTaskId(List<String> list) {
        return list.stream()
                .map(e -> {
                    String[] str = e.split("-");
                    return new TaskId(str[0], str[1]);
                }).toList();
    }

    public List<String> convertTaskIdToString(List<TaskId> list) {
        return list.stream()
                .map(e -> e.getClient() + "-" + e.getClientId())
                .toList();
    }

    public List<String> convertPLDtasksToString(List<PLDtasks> list) {
        return list.stream()
                .map(PLDtasks::getTask)
                .toList();
    }
}
