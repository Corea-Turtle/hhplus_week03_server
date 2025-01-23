package kr.hhplus.be.server.interfaces.dto.usercoupon.response;

import kr.hhplus.be.server.domain.coupon.Coupon;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.usercoupon.UserCoupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class UserCouponResponse {

    private Long userCouponId;
    private User user;
    private Coupon coupon;
    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;
    private boolean isUsed;

    public UserCouponResponse(UserCoupon userCoupon) {
    }
}

