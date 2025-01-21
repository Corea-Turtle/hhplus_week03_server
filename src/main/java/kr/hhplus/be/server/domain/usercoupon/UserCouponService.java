package kr.hhplus.be.server.domain.usercoupon;

import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.infrastructure.usercoupon.UserCouponJpaRepository;
import kr.hhplus.be.server.infrastructure.usercoupon.UserCouponRepositoryImpl;
import kr.hhplus.be.server.interfaces.dto.usercoupon.response.UserCouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserCouponService {

    private final UserCouponRepositoryImpl userCouponRepositoryImpl;

    public List<UserCoupon> getCouponsUserHave(User user) {

        return userCouponRepositoryImpl.findAllByUser(user);
    }
}
