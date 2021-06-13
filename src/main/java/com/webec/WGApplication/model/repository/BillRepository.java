package com.webec.WGApplication.model.repository;

import com.webec.WGApplication.model.BillEntry;
import com.webec.WGApplication.model.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Integer> {
   List<Bill>findByCreator(int creator);

   List<Bill> findByUserIDsContaining(int id);
}
