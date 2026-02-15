package com.hxande.cupom.repositories;

import com.hxande.cupom.models.Coupon;
import com.hxande.cupom.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, UUID> {
    Optional<Coupon> findByCodeAndStatusNot(String code, Status status);
}
