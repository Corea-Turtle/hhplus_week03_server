package kr.hhplus.be.server.domain.coupon;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.usercoupon.UserCoupon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name="coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CouponType type;

    private double valueOfType; //type별 들어가는 값 (ex 정률 할인시 10%할인이면 10, 정액할인시 - 금액 1000원 할인)

    private int remainQuantity; //발급 수량

    private boolean couponState;

    @Column(nullable = true)
    private LocalDate expiredDate; // null이면 무제한 사용;

    private LocalDate  createDate; // 발앵일

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCoupon> userCoupons = new HashSet<>();

    public Coupon(Long id, CouponType type, double valueOfType, int remainQuantity, LocalDate expiredDate, LocalDate createDate) {
        this.id = id;
        this.type = type;
        this.valueOfType = valueOfType;
        this.remainQuantity = remainQuantity;
        this.couponState = true;
        this.expiredDate = expiredDate;
        this.createDate = createDate;
    }

    public Coupon(CouponType type, double valueOfType, int remainQuantity, LocalDate expiredDate, LocalDate createDate) {
        this.type = type;
        this.valueOfType = valueOfType;
        this.remainQuantity = remainQuantity;
        this.couponState = true;
        this.expiredDate = expiredDate;
        this.createDate = createDate;
    }

    public Coupon(Long id, int remainQuantity) {
        this.id = id;
        this.remainQuantity = remainQuantity;
    }

    public Coupon(Coupon coupon) {
    }

    public boolean checkCouponRemainQuantity(Long couponId){
        if(getRemainQuantity()<=0){
            return false;
        }
        return true;
    }

    public void updateRemainQuantity(int quantity){
        this.remainQuantity = quantity;
    }

    public void addUserCoupons(Coupon coupon, User user){
        this.userCoupons.add(new UserCoupon(user, coupon));
    }

    public void issueCouponToUser(Coupon coupon, User user) {
        if(checkCouponRemainQuantity(coupon.id)) {
            addUserCoupons(coupon, user);
            updateRemainQuantity(this.remainQuantity-1);
        }
        else{
            throw new IllegalArgumentException("쿠폰이 모두 발급되었습니다.");
        }
    }


}
