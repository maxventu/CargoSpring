package com.itechart.kalenik.dao.repository;

import com.itechart.kalenik.dao.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
