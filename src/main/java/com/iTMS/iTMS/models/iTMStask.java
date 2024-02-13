package com.iTMS.iTMS.models;

import com.iTMS.iTMS.dto.TaskId;
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
@NamedNativeQuery(name = "getListOfTasksProcessedByEmployee",
        query = """
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
        resultSetMapping = "getListOfTasksProcessedByEmployee.ResultsMapping")
@SqlResultSetMapping(name = "getListOfTasksProcessedByEmployee.ResultsMapping",
        classes = @ConstructorResult(
                targetClass = TaskId.class,
                columns = {
                        @ColumnResult(name = "client", type = String.class),
                        @ColumnResult(name = "clientId", type = String.class)
                }
        ))
@NamedNativeQuery(name = "getTaskTimesheetPLD",
        query = """
                select distinct
                    p.client client,
                    p.id clientId,
                    t.employee emp,
                    sum(t.hours) hrs
                from tmsodbc.action act
                    join tmsodbc.problem p ON act.pr_ac = p.aa_id
                    join tmsodbc.timesheet t ON p.client = t.client and p.id = t.taskid
                where
                    act.assignedto = ? and
                    act.ACTDATETIME between to_date(?,'YYYY-MM-DD HH24:MI:SS') and to_date(?, 'YYYY-MM-DD HH24:MI:SS') and
                    p.status = ? and
                    t.employee in ('TOMASZ A', 'KAROL', 'RAFAL CY', 'RAFAL KAC', 'JKOPYCI', 'RAFAL MA', 'MARCINMA', 'DANIEL O',
                        'PATRYK P', 'DAMIAN SK', 'PWOJCI', 'MICHAL ZA', 'KATARZYWO', 'IWONA', 'URSZUL', 'MARTA KU', 'PAULINA T',
                        'DAMIAN L', 'EPIEKARZ', 'PSTANKIE', 'MALGOR W', 'KONRAD BI', 'PIOTR BI', 'MICHAL B', 'ANNA BU', 'PCISEK',
                        'KONRAD C', 'MIROSLAW', 'JAROSLA', 'LUKASZ D', 'PAWEL DR', 'OSKAR', 'JACEK D', 'ARKAD D', 'JOANNA G',
                        'ALEKSAN G', 'KAMIL H', 'WITOLD K', 'MACIEJ K', 'ADAM', 'ZOFIA K', 'LUKASZKOP', 'RAFAL KO', 'BARTOS K',
                        'JANUSZ KO', 'BARTLOM K', 'SLAWOMIRK', 'PATRYK M', 'BARTLOM P', 'GRZEGOR P', 'ALEKSAN P', 'BARTLO',
                        'PAWEL POK', 'TARAS P', 'KACPER S', 'JAKUB SIK', 'MAREK S', 'TOMASZ SM', 'DAMIAN SM', 'MATEUSZST', 'KRZYS T',
                        'ROBERT T', 'PATRYK W', 'LUKASZ WO', 'PAWEL ZA', 'BARTLOM Z', 'MACIEJ B', 'JAKOBUS J', 'JACEK N', 'NORBERTP',
                        'PIOTR R', 'MALGORZA', 'JAKUB', 'DANIELD', 'KONRAD PY', 'MAREK SA')
                group by t.employee, p.client, p.id
                order by 1,2""",
        resultSetMapping = "getTaskTimesheetPLD.ResultsMapping")
@SqlResultSetMapping(name = "getTaskTimesheetPLD.ResultsMapping",
        classes = @ConstructorResult(
                targetClass = TimesheetsDTO.class,
                columns = {
                        @ColumnResult(name = "client", type = String.class),
                        @ColumnResult(name = "clientId", type = String.class),
                        @ColumnResult(name = "emp", type = String.class),
                        @ColumnResult(name = "hrs", type = Double.class)
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
