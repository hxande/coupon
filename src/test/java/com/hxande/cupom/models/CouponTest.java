package com.hxande.cupom.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CouponTest {

    @Test
    void shouldNotAllowExpirationInPast() {
        assertThrows(IllegalArgumentException.class, () ->
                new Coupon(
                        "ABC123",
                        "desc",
                        new BigDecimal("1.0"),
                        OffsetDateTime.now().minusDays(1),
                        false
                )
        );
    }

    @Test
    void shouldSanitizeCode() {
        Coupon coupon = new Coupon(
                "AB-C!12@3",
                "desc",
                new BigDecimal("1.0"),
                OffsetDateTime.now().plusDays(1),
                false
        );

        assertEquals("ABC123", coupon.getCode());
    }
}
