package kr.hhplus.be.server.interfaces.api.controller.coupon;

import kr.hhplus.be.server.application.coupon.LimitedCouponIssueFacade;
import kr.hhplus.be.server.domain.coupon.CouponService;
import kr.hhplus.be.server.interfaces.dto.coupon.request.CouponCreateRequest;
import kr.hhplus.be.server.interfaces.dto.coupon.request.CouponLimitedIssueRequest;
import kr.hhplus.be.server.interfaces.dto.coupon.request.CouponUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/coupon")
public class CouponController {

    private final CouponService couponService;
    private final LimitedCouponIssueFacade limitedCouponIssueFacade;
    //1.선책순 쿠폰 발급 API - 파사드에 하자

    //쿠폰 생성
    @PostMapping("/new")
    public void createCoupon(@RequestBody CouponCreateRequest request){
        couponService.createCoupon(request);
    }

    //쿠폰 업데이트
    @PutMapping("/update")
    public void updateCoupon(@RequestBody CouponUpdateRequest request){
        couponService.updateCoupon(request);
    }

    @PostMapping("/issue")
    public void issuelimitedCoupon(@RequestBody CouponLimitedIssueRequest request){
        limitedCouponIssueFacade.issuelimitedCoupon(request);
    }
}
