package com.raffa.coupon.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponEntity {

    @Id
    private UUID id;

    private String code;

    private String description;

    private BigDecimal discountValue;

    private LocalDate expirationDate;

    private boolean published;

    private boolean deleted;
}