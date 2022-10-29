package com.example.hbmsserver.dao;

import com.example.hbmsserver.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<Transaction,Long> {
}
