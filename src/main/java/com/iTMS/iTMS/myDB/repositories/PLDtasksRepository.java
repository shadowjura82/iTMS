package com.iTMS.iTMS.myDB.repositories;

import com.iTMS.iTMS.myDB.models.PLDtasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PLDtasksRepository extends JpaRepository<PLDtasks, Long> {
    List<PLDtasks> findTaskByifTaskIsProcessedByPLDAndStatus(Boolean flag, String status);
}
