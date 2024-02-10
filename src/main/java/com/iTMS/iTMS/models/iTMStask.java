package com.iTMS.iTMS.models;

import com.iTMS.iTMS.dto.TimesheetsDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@NamedNativeQuery(name = "getTimesheetsByTaskId",
        query = """
                select
                    client,
                    taskid,
                    employee,
                    sum(hours) hr
                from timesheet
                where client = ? and
                    taskId = ?
                group by client, taskid, employee""",
        resultSetMapping = "getTimesheetsByTaskId.ResultsMapping")
@SqlResultSetMapping(name = "getTimesheetsByTaskId.ResultsMapping",
        classes = @ConstructorResult(
                targetClass = TimesheetsDTO.class,
                columns = {
                        @ColumnResult(name = "client", type = String.class),
                        @ColumnResult(name = "taskid", type = String.class),
                        @ColumnResult(name = "employee", type = String.class),
                        @ColumnResult(name = "hr", type = Double.class)
                }
        ))
@Table(name = "problem")
@Data
public class iTMStask {
    @Id
    @Column(name = "AA_ID")
    private Long id;
    @Column(name = "CLIENT")
    private String client;
    @Column(name = "ID")
    private String clientId;
}
