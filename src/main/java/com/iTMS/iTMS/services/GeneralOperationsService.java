package com.iTMS.iTMS.services;

import java.util.List;

public interface GeneralOperationsService {
    List<String> getListOfTasksProcessedByEmployee(String bin, String dateFrom, String dateTo, String status);
}
