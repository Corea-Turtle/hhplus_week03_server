package kr.hhplus.be.server.domain.coupon;

import kr.hhplus.be.server.infrastructure.coupon.CouponJpaRepository;
import kr.hhplus.be.server.interfaces.dto.coupon.request.CouponCreateRequest;
import kr.hhplus.be.server.interfaces.dto.coupon.request.CouponUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponJpaRepository couponRepository;
    //- 선착순 쿠폰 발급 API  및  보유 쿠폰 목록 조회 API 를 작성합니다.(둘다 userCoupon 파사드에 작성)
    //- 사용자는 선착순으로 할인 쿠폰을 발급받을 수 있습니다.
    //- 주문 시에 유효한 할인 쿠폰을 함께 제출하면, 전체 주문금액에 대해 할인 혜택을 부여받을 수 있습니다.

    //1. 선착순 쿠폰 발급 API - 파사드 또는 UseCase에 생성

    //1-1 쿠폰 발급 api 보다 선행되는 service
    //쿠폰 생성
    public void createCoupon(CouponCreateRequest request){
        Coupon coupon = new Coupon(request.getType(), request.getValueOfType(), request.getRemainQuantity(), request.getExpiredDate(), request.getCreateDate());
        couponRepository.save(coupon);
    }

    //쿠폰 업데이트
    public void updateCoupon(CouponUpdateRequest request){
        Coupon coupon = new Coupon(request.getCouponId(), request.getUpdateQuantity());
        couponRepository.save(coupon);
    }

    //쿠폰 조회
    public Optional<Coupon> getCouponById(Long couponId){
        Optional<Coupon> coupon = couponRepository.findById(couponId);
        return coupon;
    }
}
