package com.iTMS.iTMS.utilities;

import com.iTMS.iTMS.dto.TaskId;
import com.iTMS.iTMS.services.GeneralOperationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CheckPLDtimeSpend {
    @Autowired
    private GeneralOperationsService generalService;

    public String getComparison(String dateFrom, String dateTo, String status, List<TaskId> supportTasks) {
//        log.info("Getting tasks processed by PLD without support");
//        List<TaskId> pldTasks = generalService.getListOfTasksProcessedByEmployee("N/GENESPL", dateFrom, dateTo, "C");
//        log.info("Remove duplicates");
//        List<TaskId> tmp = pldTasks.stream()
//                .filter(e -> supportTasks.contains(e))
//                .toList();
        return null;
    }
}
