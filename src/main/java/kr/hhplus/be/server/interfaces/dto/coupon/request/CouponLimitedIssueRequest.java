package kr.hhplus.be.server.interfaces.dto.coupon.request;

import kr.hhplus.be.server.domain.coupon.Coupon;
import kr.hhplus.be.server.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouponLimitedIssueRequest {

    private User user;
    private Coupon coupon;

}
