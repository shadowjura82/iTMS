package myDB.repositories;

import myDB.models.PostgreSQLTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgreSQLTaskRepository extends JpaRepository<PostgreSQLTask, Long> {
}
