package kr.hhplus.be.server.domain.usercoupon;

import kr.hhplus.be.server.infrastructure.usercoupon.UserCouponJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("Test")
@ExtendWith(MockitoExtension.class)
class UserCouponServiceTest {

    @Mock
    UserCouponJpaRepository userCouponJpaRepository;


    @DisplayName("유저아이디를 받아, 해당 유저가 가진 Coupon 리스트를 불러온다. 존재하지 않는 경우 null을 ")
    @Test
    void getCouponsUserHave() {



    }
}