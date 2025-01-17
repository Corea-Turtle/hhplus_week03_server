package kr.hhplus.be.server.domain.coupon;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.user.User;
import kr.hhplus.be.server.domain.user.user_coupon.UserCoupon;
import lombok.Getter;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name="coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    @Enumerated(EnumType.STRING)
    private CouponType type;

    private double valueOfType; //type별 들어가는 값 (ex 정률 할인시 10%할인이면 10, 정액할인시 - 금액 1000원 할인)

    private int remainQuantity; //발급 수량

    @Column(nullable = true)
    private SimpleDateFormat expiredDate; // null이면 무제한 사용;

    private SimpleDateFormat createDate; // 발앵일

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCoupon> userCoupons = new HashSet<>();


    public Coupon(CouponType type, double valueOfType, int remainQuantity, SimpleDateFormat expiredDate, SimpleDateFormat createDate) {
        this.type = type;
        this.valueOfType = valueOfType;
        this.remainQuantity = remainQuantity;
        this.expiredDate = expiredDate;
        this.createDate = createDate;
    }

    public Coupon(Long couponId, int remainQuantity) {
        this.couponId = couponId;
        this.remainQuantity = remainQuantity;
    }

    public void updateRemainQuantity(int quantity){
        this.remainQuantity = quantity;
    }

    public void issueCouponToUser(Coupon coupon, User user) {
        if(remainQuantity!=0) {
            this.userCoupons.add(new UserCoupon(user, coupon));
            updateRemainQuantity(this.remainQuantity-1);
        }
    }
}
