package com.saper.clinicalotus.repository;


import com.saper.clinicalotus.model.PlanoDeSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoDeSaudeRepository extends JpaRepository<PlanoDeSaude,Long> {
}

