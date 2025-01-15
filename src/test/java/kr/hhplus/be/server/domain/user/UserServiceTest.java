package kr.hhplus.be.server.domain.user;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.infrastructure.user.UserJpaRepository;
import kr.hhplus.be.server.interfaces.dto.user.request.UserPointUpdateRequest;
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
        User userFound = userJpaRepository.findById(user.getUserId())
                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않습니다."));

        //then
        Assertions.assertThat(userFound.getPointAmount()).isEqualTo(10);
    }

    @DisplayName("유저아이디를 조회한 후 해당 유저 아이디의 포인트를 더한 값으로 업데이트한다.")
    @Test
    void updatePointPositiveAmountById() {
        //given
        User user = new User("이유저", 10);
        userJpaRepository.save(user);

        UserPointUpdateRequest request = new UserPointUpdateRequest(user.getUserId(), 100, true);

        long currentPoint = user.getPointAmount();
        int updatePoint = request.getUpdateAmount();

        //when
        User userFound = userJpaRepository.findById(user.getUserId())
                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않습니다."));

        userFound.updatePoint(currentPoint+updatePoint);

        Assertions.assertThat(userFound.getPointAmount()).isEqualTo(110);
        //then
    }

    @DisplayName("유저아이디를 조회한 후 해당 유저 아이디의 포인트를 뺸 값으로 업데이트한다.")
    @Test
    void updatePointNegativeAmountById() {
        //given
        User user = new User("이유저", 100);
        userJpaRepository.save(user);

        UserPointUpdateRequest request = new UserPointUpdateRequest(user.getUserId(), 90, false);

        long currentPoint = user.getPointAmount();
        int updatePoint = request.getUpdateAmount();

        //when
        User userFound = userJpaRepository.findById(user.getUserId())
                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않습니다."));

        userFound.updatePoint(currentPoint-updatePoint);

        Assertions.assertThat(userFound.getPointAmount()).isEqualTo(10);
        //then
    }
}