package com.example.hbmsserver.dao;

import com.example.hbmsserver.model.Transaction;
import com.example.hbmsserver.model.CategoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.category = :categoryId")
    Collection<Transaction> findAll(Long categoryId);

    @Query("select new com.example.hbmsserver.model.CategoryDetails(t.category.id, count(t), sum(t.value))"
            + "from Transaction as t group by t.category")
    List<CategoryDetails> getCategoriesDetails();
}
