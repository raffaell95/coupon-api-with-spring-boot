package com.raffa.coupon.application.service;

import com.raffa.coupon.domain.exception.CouponNotFoundException;
import com.raffa.coupon.domain.model.Coupon;
import com.raffa.coupon.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCouponService {

    private final CouponRepository repository;

    public void execute(UUID id) {

        Coupon coupon = repository.findById(id)
                .orElseThrow(
                        () -> new CouponNotFoundException(
                                "Coupon not found"
                        )
                );

        coupon.delete();

        repository.save(coupon);
    }
}
