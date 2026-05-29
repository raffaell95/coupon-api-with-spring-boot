package com.raffa.coupon.domain.model;

import com.raffa.coupon.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CouponTest {

    @Test
    void should_create_coupon_successfully() {

        Coupon coupon = new Coupon(
                "ABC123",
                "Black Friday",
                BigDecimal.TEN,
                LocalDate.now().plusDays(10),
                true
        );

        assertNotNull(coupon);
    }

    @Test
    void should_not_create_coupon_with_past_expiration_date() {

        assertThrows(
                BusinessException.class,
                () -> new Coupon(
                        "ABC123",
                        "Coupon",
                        BigDecimal.TEN,
                        LocalDate.now().minusDays(1),
                        false
                )
        );
    }

    @Test
    void should_not_create_coupon_with_discount_less_than_minimum() {

        assertThrows(
                BusinessException.class,
                () -> new Coupon(
                        "ABC123",
                        "Coupon",
                        BigDecimal.valueOf(0.1),
                        LocalDate.now().plusDays(1),
                        false
                )
        );
    }

    @Test
    void should_soft_delete_coupon() {

        Coupon coupon = new Coupon(
                "ABC123",
                "Coupon",
                BigDecimal.TEN,
                LocalDate.now().plusDays(1),
                false
        );

        coupon.delete();

        assertTrue(coupon.isDeleted());
    }

    @Test
    void should_not_delete_coupon_twice() {

        Coupon coupon = new Coupon(
                "ABC123",
                "Coupon",
                BigDecimal.TEN,
                LocalDate.now().plusDays(1),
                false
        );

        coupon.delete();

        assertThrows(
                BusinessException.class,
                coupon::delete
        );
    }
}