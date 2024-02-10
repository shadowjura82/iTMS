package com.iTMS.iTMS.repositories;

import com.iTMS.iTMS.dto.TimesheetsDTO;
import com.iTMS.iTMS.models.iTMStask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OracleTaskRepository extends JpaRepository<iTMStask, Long> {
    @Query(value = """
            select distinct
                p.client client,
                p.id clientId
            from tmsodbc.action act
                 join tmsodbc.problem p ON act.pr_ac = p.aa_id
            where
                 act.assignedto = ? and
                 act.ACTDATETIME between to_date(?,'YYYY-MM-DD HH24:MI:SS') and to_date(?, 'YYYY-MM-DD HH24:MI:SS')
                 and p.status = ?
            order by 1,2""",
            nativeQuery = true)
    List<String> getListOfTasksProcessedByEmployee(String bin, String dateFrom, String dateTo, String status);

    @Query(name = "getTimesheetsByTaskId", nativeQuery = true)
    List<TimesheetsDTO> getTimesheets(String client, String taskId);

    @Query(value = "select status from problem where client = ? and id = ?", nativeQuery = true)
    String getTaskStatus(String client, String id);
}
