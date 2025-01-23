package kr.hhplus.be.server.application.coupon;

import kr.hhplus.be.server.domain.coupon.Coupon;
import kr.hhplus.be.server.domain.coupon.CouponRepository;
import kr.hhplus.be.server.domain.coupon.CouponType;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.user.UserRepository;
import kr.hhplus.be.server.domain.usercoupon.UserCoupon;
import kr.hhplus.be.server.domain.usercoupon.UserCouponRepository;
import kr.hhplus.be.server.domain.usercoupon.UserCouponService;
import kr.hhplus.be.server.infrastructure.coupon.CouponJpaRepository;
import kr.hhplus.be.server.infrastructure.coupon.CouponRepositoryImpl;
import kr.hhplus.be.server.infrastructure.user.UserJpaRepository;
import kr.hhplus.be.server.infrastructure.user.UserRepositoryImpl;
import kr.hhplus.be.server.infrastructure.usercoupon.UserCouponJpaRepository;
import kr.hhplus.be.server.infrastructure.usercoupon.UserCouponRepositoryImpl;
import kr.hhplus.be.server.interfaces.dto.usercoupon.response.UserCouponResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


@ActiveProfiles("test")
@SpringBootTest
class LimitedCouponIssueFacadeTest {

    //유저쿠폰
    @Autowired
    private UserCouponService userCouponService;

    @Autowired
    private UserCouponRepositoryImpl userCouponRepositoryImpl;

    //유저
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    //쿠폰
    @Autowired
    private CouponRepositoryImpl couponRepositoryImpl;


    @DisplayName("선착순 할인 쿠폰을 특정 유저에게 발행한다. 유저가 이미 쿠폰을 가지고 있어서 실패하는 결과를 반환한다.")
    @Test
    void issuelimitedCouponUserHaveCoupon() {
        // given
        // 유저, 쿠폰 생성
        User user = new User("유저1", 2000);
        Coupon coupon = new Coupon(CouponType.RATE, 10, 2, LocalDate.parse("2025-01-25"), LocalDate.now());
        userRepositoryImpl.save(user);
        couponRepositoryImpl.save(coupon);

        userCouponRepositoryImpl.save(new UserCoupon(user, coupon));

        // when & then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            // 1. 해당 유저가 동일 쿠폰을 가지고 있는지 확인
            List<Coupon> couponsUserHave = userCouponService.getCouponsUserHave(user)
                    .stream()
                    .map(UserCoupon::getCoupon) // UserCouponResponse에서 Coupon 추출
                    .toList(); // 추출한 Coupon을 List로 변환

            boolean couponFinded = couponsUserHave.stream()
                    .anyMatch(userHave -> userHave.getId().equals(coupon.getId()));

            if (couponFinded) {
                throw new IllegalArgumentException("이미 해당 쿠폰을 가지고 있습니다.");
            }

            // 2. 해당 쿠폰의 개수가 0보다 큰지 확인
            coupon.checkCouponRemainQuantity(coupon.getId());

            // 3. 쿠폰 발급 (동시성 처리)
            user.addUserCoupon(user, coupon);
            coupon.issueCouponToUser(coupon, user);
        });

        // 예외 메시지 확인
        assertEquals("이미 해당 쿠폰을 가지고 있습니다.", exception.getMessage());
    }

    @DisplayName("선착순 할인 쿠폰을 특정 유저에게 발행한다. 유저에게 해당 쿠폰이 없으면 발행 성공시킨다.")
    @Test
    void issuelimitedCouponU() {
        //given
        //유저, 쿠폰 생성
        User user1 = new User("유저1",2000);
        User user2 = new User("유저2",5000);

        Coupon coupon = new Coupon(CouponType.RATE, 10, 2, LocalDate.parse("2025-01-25"), LocalDate.now());
        userRepositoryImpl.save(user1);
        userRepositoryImpl.save(user2);
        couponRepositoryImpl.save(coupon);

        userCouponRepositoryImpl.save(new UserCoupon(user1,coupon));

        //1.해당 유저가 동일 쿠폰을 가지고 있는지 확인 하고 없으면 진행(있으면 true)
        List<Coupon> couponsUserHave =  userCouponService.getCouponsUserHave(user2)
                .stream()
                .map(UserCoupon::getCoupon) // UserCouponResponse에서 Coupon 추출
                .toList();   // 추출한 Coupon을 List로 변환

        System.out.println("CouponList : " +  couponsUserHave);

        boolean couponFinded = couponsUserHave.stream()
                .anyMatch(userHave -> userHave.getId().equals(coupon.getId()));

        if(couponFinded){
            throw new IllegalArgumentException("이미 해당 쿠폰을 가지고 있습니다.");
        }

        //2.해당 쿠폰의 개수가 0보다 큰지 확인
        coupon.checkCouponRemainQuantity(coupon.getId());

        //3.쿠폰 발급(동시성 처리)
        user2.addUserCoupon(user2,coupon);
        coupon.issueCouponToUser(coupon,user2);
    }
}