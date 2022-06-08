package com.example.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.entities.DatabaseChangeLog;
import com.example.entities.DatabaseChangeLogId;

@Repository
public interface DatabaseChangeLogRepository extends JpaRepository<DatabaseChangeLog, DatabaseChangeLogId> {

	@Query(value = "SELECT * FROM DATABASECHANGELOG ORDER BY ORDEREXECUTED DESC", nativeQuery = true)
	List<DatabaseChangeLog> findAllChangeLogs();
	
	@Query(value = "SELECT COUNT(*) FROM DATABASECHANGELOG WHERE LOWER(EXECTYPE) = LOWER(?1)", nativeQuery = true)
	Long countOfExecutions(String execType);
}
