package com.hxande.cupom.services;

import com.hxande.cupom.models.Coupon;
import com.hxande.cupom.repositories.CouponRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CouponService {

    private final CouponRepository repository;

    public CouponService(CouponRepository repository) {
        this.repository = repository;
    }

    public Coupon create(String code,
                         String description,
                         BigDecimal discount,
                         OffsetDateTime expiration,
                         boolean published) {

        Coupon coupon = new Coupon(code, description, discount, expiration, published);
        return repository.save(coupon);
    }

    public void delete(UUID id) {
        Coupon coupon = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        coupon.delete();
    }

    public List<Coupon> findAll() {
        return repository.findAll();
    }

    public Coupon findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
    }
}
