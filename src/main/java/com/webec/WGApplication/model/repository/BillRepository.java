package com.webec.WGApplication.model.repository;

import com.webec.WGApplication.model.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    Optional<Bill> findByCreator(int creator);

}
