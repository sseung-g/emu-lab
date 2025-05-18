package com.practice.emulab.repository;

import com.practice.emulab.domain.CycleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleDataRepository extends JpaRepository<CycleData, Long> {
}