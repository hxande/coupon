package com.hxande.cupom.api;

import com.hxande.cupom.models.Coupon;
import com.hxande.cupom.services.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService service;

    public CouponController(CouponService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CouponResponse>> findAll() {

        List<CouponResponse> response = service.findAll()
                .stream()
                .map(CouponResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> findById(@PathVariable UUID id) {

        Coupon coupon = service.findById(id);

        return ResponseEntity.ok(CouponResponse.from(coupon));
    }

    @PostMapping
    public ResponseEntity<Coupon> create(@RequestBody CreateCouponRequest request) {
        Coupon coupon = service.create(
                request.code(),
                request.description(),
                request.discountValue(),
                request.expirationDate(),
                request.published()
        );
        return ResponseEntity.ok(coupon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
