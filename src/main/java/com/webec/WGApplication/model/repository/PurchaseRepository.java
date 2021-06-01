package com.webec.WGApplication.model.repository;

import com.webec.WGApplication.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

}
