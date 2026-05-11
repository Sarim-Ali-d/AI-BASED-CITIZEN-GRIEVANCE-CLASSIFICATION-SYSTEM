package com.grievance.repository;

import com.grievance.Entity.grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface grievenceRepository extends JpaRepository<grievance, Long> {

}
