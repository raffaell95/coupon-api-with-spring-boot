package com.raffa.coupon.application.service;

import com.raffa.coupon.domain.exception.CouponNotFoundException;
import com.raffa.coupon.domain.model.Coupon;
import com.raffa.coupon.domain.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteCouponServiceTest {

    @Mock
    private CouponRepository repository;

    @InjectMocks
    private DeleteCouponService service;

    @Test
    void should_delete_coupon() {

        Coupon coupon = new Coupon(
                "ABC123",
                "Coupon",
                BigDecimal.TEN,
                LocalDate.now().plusDays(1),
                false
        );

        when(repository.findById(any()))
                .thenReturn(Optional.of(coupon));

        service.execute(UUID.randomUUID());

        assertTrue(coupon.isDeleted());

        verify(repository)
                .save(coupon);
    }

    @Test
    void should_throw_when_coupon_not_found() {

        when(repository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(
                CouponNotFoundException.class,
                () -> service.execute(UUID.randomUUID())
        );
    }
}
