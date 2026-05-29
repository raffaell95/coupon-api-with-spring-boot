package com.raffa.coupon.domain.model;

import com.raffa.coupon.domain.exception.BusinessException;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class Coupon {

    private UUID id;
    private CouponCode code;
    private String description;
    private BigDecimal discountValue;
    private LocalDate expirationDate;
    private boolean published;
    private boolean deleted;

    public Coupon(
            String code,
            String description,
            BigDecimal discountValue,
            LocalDate expirationDate,
            boolean published
    ) {

        validateDiscount(discountValue);
        validateExpiration(expirationDate);

        this.id = UUID.randomUUID();
        this.code = new CouponCode(code);
        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.published = published;
        this.deleted = false;
    }

    private Coupon(
            UUID id,
            CouponCode code,
            String description,
            BigDecimal discountValue,
            LocalDate expirationDate,
            boolean published,
            boolean deleted
    ) {

        this.id = id;
        this.code = code;
        this.description = description;
        this.discountValue = discountValue;
        this.expirationDate = expirationDate;
        this.published = published;
        this.deleted = deleted;
    }

    public static Coupon restore(
            UUID id,
            String code,
            String description,
            BigDecimal discountValue,
            LocalDate expirationDate,
            boolean published,
            boolean deleted
    ) {

        return new Coupon(
                id,
                new CouponCode(code),
                description,
                discountValue,
                expirationDate,
                published,
                deleted
        );
    }

    public static Coupon restore(String code, String description, BigDecimal bigDecimal, LocalDate localDate, boolean published) {
        return null;
    }

    public void delete() {

        if (deleted) {
            throw new BusinessException(
                    "Coupon already deleted"
            );
        }

        this.deleted = true;
    }

    private void validateDiscount(BigDecimal value) {

        if (value.compareTo(BigDecimal.valueOf(0.5)) < 0) {
            throw new BusinessException(
                    "Discount value must be greater than 0.5"
            );
        }
    }

    private void validateExpiration(LocalDate date) {

        if (date.isBefore(LocalDate.now())) {
            throw new BusinessException(
                    "Expiration date cannot be in the past"
            );
        }
    }
}