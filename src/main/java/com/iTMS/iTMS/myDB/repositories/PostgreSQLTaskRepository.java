package com.iTMS.iTMS.myDB.repositories;

import com.iTMS.iTMS.myDB.models.PostgreSQLTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgreSQLTaskRepository extends JpaRepository<PostgreSQLTask, Long> {
}
