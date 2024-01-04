package com.iTMS.iTMS.repositories;

import com.iTMS.iTMS.models.OracleTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OracleTaskRepository extends JpaRepository<OracleTask, Long> {
    @Query(value = "select client || '-' || id from problem where employee = 'N/GENEISD' and status != 'C'", nativeQuery = true)
    public List<String> getTasksAssignedToSupport();
}
