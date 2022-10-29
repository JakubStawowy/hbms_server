package com.example.hbmsserver.dao;

import com.example.hbmsserver.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryTypesDao extends JpaRepository<CategoryType, Long> {
}