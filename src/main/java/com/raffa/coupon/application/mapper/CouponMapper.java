package com.raffa.coupon.application.mapper;

import com.raffa.coupon.application.dto.response.CouponResponse;
import com.raffa.coupon.domain.model.Coupon;

public class CouponMapper {

    public static CouponResponse toResponse(Coupon coupon) {

        return new CouponResponse(
                coupon.getId(),
                coupon.getCode().getValue(),
                coupon.getDescription(),
                coupon.getDiscountValue(),
                coupon.getExpirationDate(),
                coupon.isPublished()
        );
    }
}