package kr.hhplus.be.server.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
