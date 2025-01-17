package kr.hhplus.be.server.domain.user;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.user.user_coupon.UserCoupon;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name="user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 255)
    private String userName;

    @ColumnDefault("0")
    private long pointAmount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserCoupon> userCoupons = new HashSet<>();

    @Builder
    public User(Long userId, String userName, long pointAmount) {
        this.userId = userId;
        this.userName = userName;
        this.pointAmount = pointAmount;
    }

    public User(String userName, long pointAmount){
        this.userName = userName;
        this.pointAmount = pointAmount;
    }

    public void updatePoint(long pointAmount){
        this.pointAmount = pointAmount;
    }
}
