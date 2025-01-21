package kr.hhplus.be.server.interfaces.dto.coupon.request;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import kr.hhplus.be.server.domain.coupon.CouponType;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CouponCreateRequest {
    @Enumerated(EnumType.STRING)
    private CouponType type;
    private double valueOfType;
    private int remainQuantity;
    private LocalDate expiredDate = null;
    private LocalDate createDate;

}
