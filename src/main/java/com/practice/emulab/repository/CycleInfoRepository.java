package com.practice.emulab.repository;

import com.practice.emulab.domain.CycleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleInfoRepository extends JpaRepository<CycleInfo, Long> {
}