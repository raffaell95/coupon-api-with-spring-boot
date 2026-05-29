package com.raffa.coupon.domain.model;

import com.raffa.coupon.domain.exception.BusinessException;

public class CouponCode {

    private final String value;

    public CouponCode(String code) {

        String sanitized = sanitize(code);

        if (sanitized.length() != 6) {
            throw new BusinessException(
                    "Coupon code must contain 6 characters"
            );
        }

        this.value = sanitized;
    }

    private String sanitize(String code) {

        return code
                .replaceAll("[^a-zA-Z0-9]", "")
                .toUpperCase();
    }

    public String getValue() {
        return value;
    }
}
