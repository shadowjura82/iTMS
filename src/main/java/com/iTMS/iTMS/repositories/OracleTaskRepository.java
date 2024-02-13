package com.iTMS.iTMS.repositories;

import com.iTMS.iTMS.dto.TaskId;
import com.iTMS.iTMS.dto.TimesheetsDTO;
import com.iTMS.iTMS.models.iTMStask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OracleTaskRepository extends JpaRepository<iTMStask, Long> {

    @Query(name = "getListOfTasksProcessedByEmployee", nativeQuery = true)
    List<TaskId> getListOfTasksProcessedByEmployee(String bin, String dateFrom, String dateTo, String status);

    @Query(name = "getTimesheetsByTaskId", nativeQuery = true)
    List<TimesheetsDTO> getTimesheets(String client, String taskId);

    @Query(value = "select status from problem where client = ? and id = ?", nativeQuery = true)
    String getTaskStatus(String client, String id);

    @Query(name = "getTaskTimesheetPLD", nativeQuery = true)
    List<TimesheetsDTO> getTaskTimesheetPLD(String bin, String dateFrom, String dateTo, String status);
}
