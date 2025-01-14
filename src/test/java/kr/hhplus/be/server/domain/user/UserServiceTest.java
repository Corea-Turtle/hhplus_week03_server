package kr.hhplus.be.server.domain.user;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.infrastructure.user.UserJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("Test")
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserJpaRepository userJpaRepository;

    @DisplayName("유저아이디를 입력하여 해당 아이디의 현재 포인트를 조회한다.")
    @Test
    void getCurrentPointAmount() {
        //given
        User user = new User( "김유저", 10);
        userJpaRepository.save(user);

        //when
        User userFinded = userJpaRepository.findById(user.getUserId())
                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않습니다."));

        //then
        Assertions.assertThat(userFinded.getPointAmount()).isEqualTo(10);
    }
}