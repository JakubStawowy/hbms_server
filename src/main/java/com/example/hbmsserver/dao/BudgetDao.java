package com.example.hbmsserver.dao;

import com.example.hbmsserver.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetDao extends JpaRepository<Budget,Long> {
}