package kr.hhplus.be.server.interfaces.dto.coupon.request;

import lombok.Getter;


@Getter
public class CouponUpdateRequest {

    private Long couponId;
    private int updateQuantity;

    public CouponUpdateRequest(Long couponId, int updateQuantity) {
        this.couponId = couponId;
        this.updateQuantity = updateQuantity;
    }
}
