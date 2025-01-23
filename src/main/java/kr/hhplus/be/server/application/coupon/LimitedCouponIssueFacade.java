package kr.hhplus.be.server.application.coupon;

import kr.hhplus.be.server.domain.coupon.Coupon;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.usercoupon.UserCoupon;
import kr.hhplus.be.server.domain.usercoupon.UserCouponService;
import kr.hhplus.be.server.interfaces.dto.coupon.request.CouponLimitedIssueRequest;
import kr.hhplus.be.server.interfaces.dto.usercoupon.response.UserCouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LimitedCouponIssueFacade {

    private final UserCouponService userCouponService;


    public void issuelimitedCoupon(CouponLimitedIssueRequest request){
        //1.해당 유저가 동일 쿠폰을 가지고 있는지 확인 하고 없으면 진행(있으면 true)
        User user = request.getUser();
        Coupon coupon = request.getCoupon();

        //1.해당 유저가 동일 쿠폰을 가지고 있는지 확인 하고 없으면 진행(있으면 true)
        List<Coupon> couponsUserHave =  userCouponService.getCouponsUserHave(user)
                .stream()
                .map(UserCoupon::getCoupon) // UserCouponResponse에서 Coupon 추출
                .toList();   // 추출한 Coupon을 List로 변환


        boolean couponFinded = couponsUserHave.stream()
                .anyMatch(userHave -> userHave.getId().equals(coupon.getId()));

        if(couponFinded){
            throw new IllegalArgumentException("이미 해당 쿠폰을 가지고 있습니다.");
        }
        //2.해당 쿠폰의 개수가 0보다 큰지 확인
        coupon.checkCouponRemainQuantity(coupon.getId());

        //3.쿠폰 발급(동시성 처리)


        userCouponService.saveCouponAndUser(user,coupon);

    }

}
