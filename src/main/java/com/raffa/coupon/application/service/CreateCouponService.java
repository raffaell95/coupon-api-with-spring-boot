package com.raffa.coupon.application.service;

import com.raffa.coupon.application.dto.request.CreateCouponRequest;
import com.raffa.coupon.application.dto.response.CouponResponse;
import com.raffa.coupon.application.mapper.CouponMapper;
import com.raffa.coupon.domain.model.Coupon;
import com.raffa.coupon.domain.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCouponService {

    private final CouponRepository repository;

    public CouponResponse execute(
            CreateCouponRequest request
    ) {

        Coupon coupon = new Coupon(
                request.code(),
                request.description(),
                request.discountValue(),
                request.expirationDate(),
                request.published()
        );

        repository.save(coupon);

        return CouponMapper.toResponse(coupon);
    }
}