package com.raffa.coupon.infrastructure.persistence.repository;

import com.raffa.coupon.infrastructure.persistence.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCouponRepository
        extends JpaRepository<CouponEntity, UUID> {
}
