package com.example.shoppear.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppear.marketplace.entity.MediosDePago;

@Repository
public interface MediosDePagoRepository extends JpaRepository<MediosDePago, Long> {
}
