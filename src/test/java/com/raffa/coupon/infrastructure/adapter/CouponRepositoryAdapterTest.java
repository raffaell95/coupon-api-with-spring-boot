package com.raffa.coupon.infrastructure.adapter;

import com.raffa.coupon.domain.model.Coupon;
import com.raffa.coupon.infrastructure.persistence.entity.CouponEntity;
import com.raffa.coupon.infrastructure.persistence.repository.JpaCouponRepository;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CouponRepositoryAdapterTest {

    @Mock
    private JpaCouponRepository repository;

    @InjectMocks
    private CouponRepositoryAdapter adapter;

    @Test
    void should_save_coupon() {

        Coupon coupon = new Coupon(
                "ABC123",
                "Black Friday",
                BigDecimal.TEN,
                LocalDate.now().plusDays(10),
                true
        );

        adapter.save(coupon);

        verify(repository)
                .save(any(CouponEntity.class));
    }

    @Test
    void should_map_coupon_entity_correctly() {

        Coupon coupon = new Coupon(
                "ABC123",
                "Black Friday",
                BigDecimal.TEN,
                LocalDate.now().plusDays(10),
                true
        );

        adapter.save(coupon);

        ArgumentCaptor<CouponEntity> captor =
                ArgumentCaptor.forClass(
                        CouponEntity.class
                );

        verify(repository)
                .save(captor.capture());

        CouponEntity entity =
                captor.getValue();

        assertEquals(
                coupon.getId(),
                entity.getId()
        );

        assertEquals(
                "ABC123",
                entity.getCode()
        );

        assertEquals(
                "Black Friday",
                entity.getDescription()
        );

        assertEquals(
                BigDecimal.TEN,
                entity.getDiscountValue()
        );

        assertTrue(entity.isPublished());

        assertFalse(entity.isDeleted());
    }

    @Test
    void should_find_coupon_by_id() {

        UUID id = UUID.randomUUID();

        CouponEntity entity =
                CouponEntity.builder()
                        .id(id)
                        .code("ABC123")
                        .description("Coupon")
                        .discountValue(BigDecimal.TEN)
                        .expirationDate(
                                LocalDate.now().plusDays(1)
                        )
                        .published(true)
                        .deleted(false)
                        .build();

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        Optional<Coupon> result =
                adapter.findById(id);

        assertTrue(result.isPresent());

        Coupon coupon = result.get();

        assertEquals(id, coupon.getId());

        assertEquals(
                "ABC123",
                coupon.getCode().getValue()
        );

        assertEquals(
                "Coupon",
                coupon.getDescription()
        );
    }

    @Test
    void should_return_empty_when_coupon_not_found() {

        UUID id = UUID.randomUUID();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        Optional<Coupon> result =
                adapter.findById(id);

        assertTrue(result.isEmpty());
    }
}
