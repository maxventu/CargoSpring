package com.itechart.kalenik.dao.repository;

import com.itechart.kalenik.dao.entity.Company;
import com.itechart.kalenik.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
