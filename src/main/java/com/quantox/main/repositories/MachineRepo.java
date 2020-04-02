package com.quantox.main.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.quantox.main.domain.Machine;

public interface MachineRepo extends CrudRepository<Machine, Long> {

	@Query("SELECT * FROM Machine WHERE UPPER(name) LIKE UPPER(%?1%)")
	List<Machine> getMachineByFilterName(String value);
	
	@Query("SELECT * FROM Machine WHERE status = ?1")
	List<Machine> getMachineByFilterStatus(String value);
										
	@Query("SELECT * FROM Machine WHERE createdAt > ?1")
	List<Machine> getMachineByFilterDateFrom(Date value);
	
	@Query("SELECT * FROM Machine WHERE createdAt < ?1")
	List<Machine> getMachineByFilterDateTo(Date value);
}
