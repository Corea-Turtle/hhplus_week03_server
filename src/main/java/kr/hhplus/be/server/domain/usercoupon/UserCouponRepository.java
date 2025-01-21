package kr.hhplus.be.server.domain.usercoupon;

import kr.hhplus.be.server.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCouponRepository{
    Optional<UserCoupon> findById(Long id);
    List<UserCoupon> findAll();
    void save(UserCoupon userCoupon);
}
