package com.iTMS.iTMS.myDB.repositories;

import com.iTMS.iTMS.myDB.models.PLDtasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PLDtasksRepository extends JpaRepository<PLDtasks, Long> {
}
