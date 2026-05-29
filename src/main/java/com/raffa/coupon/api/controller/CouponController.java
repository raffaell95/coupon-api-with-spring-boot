package com.raffa.coupon.api.controller;

import com.raffa.coupon.application.dto.request.CreateCouponRequest;
import com.raffa.coupon.application.dto.response.CouponResponse;
import com.raffa.coupon.application.service.CreateCouponService;
import com.raffa.coupon.application.service.DeleteCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CreateCouponService createCouponService;
    private final DeleteCouponService deleteCouponService;

    @PostMapping
    public ResponseEntity<CouponResponse> create(
            @RequestBody CreateCouponRequest request
    ) {

        CouponResponse response =
                createCouponService.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        deleteCouponService.execute(id);

        return ResponseEntity.noContent().build();
    }
}