package com.raffa.coupon.infrastructure.adapter;

import com.raffa.coupon.domain.model.Coupon;
import com.raffa.coupon.domain.repository.CouponRepository;
import com.raffa.coupon.infrastructure.persistence.entity.CouponEntity;
import com.raffa.coupon.infrastructure.persistence.repository.JpaCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CouponRepositoryAdapter implements CouponRepository {

    private final JpaCouponRepository repository;

    @Override
    public Coupon save(Coupon coupon) {

        CouponEntity entity = CouponEntity.builder()
                        .id(coupon.getId())
                        .code(coupon.getCode().getValue())
                        .description(coupon.getDescription())
                        .discountValue(coupon.getDiscountValue())
                        .expirationDate(coupon.getExpirationDate())
                        .published(coupon.isPublished())
                        .deleted(coupon.isDeleted())
                        .build();

        repository.save(entity);

        return coupon;
    }

    @Override
    public Optional<Coupon> findById(UUID id) {

        return repository.findById(id)
                .map(this::toDomain);
    }

    private Coupon toDomain(CouponEntity entity) {

        return Coupon.restore(
                entity.getId(),
                entity.getCode(),
                entity.getDescription(),
                entity.getDiscountValue(),
                entity.getExpirationDate(),
                entity.isPublished(),
                entity.isDeleted()
        );
    }
}