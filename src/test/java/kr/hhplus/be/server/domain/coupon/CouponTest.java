package kr.hhplus.be.server.domain.coupon;

import kr.hhplus.be.server.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class CouponTest {

    @DisplayName("쿠폰의 ID를 받아 남은 수량이 0보다 큰지 확인한다.")
    @Test
    void checkCouponRemainQuantity() {
        //given
        LocalDate createDate = LocalDate.of(2024, 1, 25);
        LocalDate expiredDate = LocalDate.of(2024, 12, 31);
        Coupon coupon = new Coupon(1L, CouponType.RATE, 10, 30, expiredDate, createDate);


        //when
        boolean hasRemaining = coupon.checkCouponRemainQuantity(1L);

        //then
        assertTrue(hasRemaining, "쿠폰의 남은 수량이 0보다 커야 합니다.");
    }

    @DisplayName("쿠폰객체와 유저객체를 받아 쿠폰 객체에 UserCoupon(유저)를 추가한 후 해당 쿠폰의 수량을 감소한다..")
    @Test
    void updateRemainQuantity() {
        //given
        LocalDate createDate = LocalDate.of(2024, 1, 25);
        LocalDate expiredDate = LocalDate.of(2024, 12, 31);
        Coupon coupon = new Coupon(1L, CouponType.RATE, 10, 1, expiredDate, createDate);
        User user = new User("김유저", 1000);

        //when
        coupon.issueCouponToUser(coupon, user);

        //then
        assertEquals(0, coupon.getRemainQuantity(), "쿠폰 수량이 1 감소해야 합니다.");
        assertFalse(coupon.checkCouponRemainQuantity(1L), "쿠폰 수량이 0이므로 발급이 불가능해야 합니다.");
    }
}