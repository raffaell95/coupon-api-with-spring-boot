package com.raffa.coupon.api.handler;


import com.raffa.coupon.domain.exception.BusinessException;
import com.raffa.coupon.domain.exception.CouponNotFoundException;

import org.junit.jupiter.api.Test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void should_handle_business_exception() {

        BusinessException exception =
                new BusinessException(
                        "Invalid coupon"
                );

        ResponseEntity<ErrorResponse> response =
                handler.handleBusiness(exception);

        assertEquals(
                HttpStatus.BAD_REQUEST,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());

        assertEquals(
                "Invalid coupon",
                response.getBody().message()
        );
    }

    @Test
    void should_handle_coupon_not_found_exception() {

        CouponNotFoundException exception =
                new CouponNotFoundException(
                        "Coupon not found"
                );

        ResponseEntity<ErrorResponse> response =
                handler.handleNotFound(exception);

        assertEquals(
                HttpStatus.NOT_FOUND,
                response.getStatusCode()
        );

        assertNotNull(response.getBody());

        assertEquals(
                "Coupon not found",
                response.getBody().message()
        );
    }
}