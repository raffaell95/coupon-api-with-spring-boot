package com.raffa.coupon.api.controller;

import com.raffa.coupon.application.dto.request.CreateCouponRequest;
import com.raffa.coupon.application.dto.response.CouponResponse;
import com.raffa.coupon.application.service.CreateCouponService;
import com.raffa.coupon.application.service.DeleteCouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponController.class)
class CouponControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateCouponService createCouponService;

    @MockitoBean
    private DeleteCouponService deleteCouponService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_coupon() throws Exception {

        CreateCouponRequest request =
                new CreateCouponRequest(
                        "ABC123",
                        "Coupon",
                        BigDecimal.TEN,
                        LocalDate.now().plusDays(10),
                        true
                );

        CouponResponse response =
                new CouponResponse(
                        UUID.randomUUID(),
                        "ABC123",
                        "Coupon",
                        BigDecimal.TEN,
                        LocalDate.now().plusDays(10),
                        true
                );

        when(createCouponService.execute(any()))
                .thenReturn(response);

        mockMvc.perform(
                        post("/coupons")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isCreated());
    }

    @Test
    void should_delete_coupon() throws Exception {

        mockMvc.perform(
                        delete("/coupons/" + UUID.randomUUID())
                )
                .andExpect(status().isNoContent());
    }
}