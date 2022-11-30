package com.example.hbmsserver.dao;

import com.example.hbmsserver.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
    @Query("select t from Category t where t.userId = :userId and t.date = :date")
    Collection<Category> findAll(Long userId, String date);

    @Override
    Optional<Category> findById(Long aLong);
}