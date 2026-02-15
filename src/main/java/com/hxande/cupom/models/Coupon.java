package com.hxande.cupom.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal discountValue;

    @Column(nullable = false)
    private OffsetDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private boolean published;

    @Column(nullable = false)
    private boolean redeemed;

    public Coupon(String code,
                  String description,
                  BigDecimal discountValue,
                  OffsetDateTime expirationDate,
                  boolean published) {

        this.code = sanitizeCode(code);
        this.description = Objects.requireNonNull(description);
        this.discountValue = validateDiscount(discountValue);
        this.expirationDate = validateExpiration(expirationDate);
        this.published = published;
        this.status = Status.ACTIVE;
    }

    private String sanitizeCode(String code) {
        if (code == null) throw new IllegalArgumentException("Code is required");

        String cleaned = code.replaceAll("[^a-zA-Z0-9]", "");

        if (cleaned.length() != 6)
            throw new IllegalArgumentException("Code must have 6 alphanumeric characters");

        return cleaned.toUpperCase();
    }

    private BigDecimal validateDiscount(BigDecimal value) {
        if (value == null || value.compareTo(new BigDecimal("0.5")) < 0)
            throw new IllegalArgumentException("Minimum discount is 0.5");

        return value;
    }

    private OffsetDateTime validateExpiration(OffsetDateTime date) {
        if (date == null || date.isBefore(OffsetDateTime.now()))
            throw new IllegalArgumentException("Expiration date cannot be in the past");

        return date;
    }

    public void delete() {
        if (this.status == Status.DELETED)
            throw new IllegalStateException("Coupon already deleted");

        this.status = Status.DELETED;
    }

    public boolean isDeleted() {
        return this.status == Status.DELETED;
    }
}
