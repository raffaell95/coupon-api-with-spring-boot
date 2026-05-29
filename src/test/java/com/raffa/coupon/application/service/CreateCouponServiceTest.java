package com.raffa.coupon.application.service;

import com.raffa.coupon.application.dto.request.CreateCouponRequest;
import com.raffa.coupon.application.dto.response.CouponResponse;
import com.raffa.coupon.domain.model.Coupon;
import com.raffa.coupon.domain.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateCouponServiceTest {

    @Mock
    private CouponRepository repository;

    @InjectMocks
    private CreateCouponService service;

    @Test
    void should_create_coupon() {

        CreateCouponRequest request =
                new CreateCouponRequest(
                        "ABC123",
                        "Black Friday",
                        BigDecimal.TEN,
                        LocalDate.now().plusDays(10),
                        true
                );

        CouponResponse response =
                service.execute(request);

        assertNotNull(response);

        verify(repository)
                .save(any(Coupon.class));
    }
}
