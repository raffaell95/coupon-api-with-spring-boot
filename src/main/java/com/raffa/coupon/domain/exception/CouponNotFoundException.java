package com.raffa.coupon.domain.exception;


public class CouponNotFoundException extends BusinessException {

    public CouponNotFoundException(String message) {
        super(message);
    }
}
