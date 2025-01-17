package kr.hhplus.be.server.domain.coupon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponType {

    RATE("정률할인"),
    DISCOUNT("정액할인");

    private final String text;
}
