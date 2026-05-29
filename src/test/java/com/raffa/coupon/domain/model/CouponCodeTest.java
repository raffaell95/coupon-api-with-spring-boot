package com.raffa.coupon.domain.model;

import com.raffa.coupon.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponCodeTest {

    @Test
    void should_remove_special_characters() {

        CouponCode code =
                new CouponCode("AB@12#34");

        assertEquals("AB1234", code.getValue());
    }

    @Test
    void should_convert_to_uppercase() {

        CouponCode code =
                new CouponCode("ab1234");

        assertEquals("AB1234", code.getValue());
    }

    @Test
    void should_throw_exception_when_code_is_invalid() {

        assertThrows(
                BusinessException.class,
                () -> new CouponCode("12")
        );
    }
}
