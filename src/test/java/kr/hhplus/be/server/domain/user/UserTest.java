package kr.hhplus.be.server.domain.user;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.interfaces.dto.user.request.UserPointUpdateRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest
@Transactional
class UserTest {


    @DisplayName("유저의 현재 포인트가 증가하여 저장된다.")
    @Test
    void updatePointPositive() {
        //given
        User user = new User(1L,"김유저",10);
        UserPointUpdateRequest request1 = new UserPointUpdateRequest(1L, 100, true);

        //when
        user.updatePoint(user.getPointAmount()+request1.getUpdateAmount());

        //then
        Assertions.assertThat(user.getPointAmount()).isEqualTo(110);
    }

    @DisplayName("유저의 현재 포인트가 감소하여 저장된다.")
    @Test
    void updatePointNegative() {
        //given
        User user = new User(1L,"김유저",100);
        UserPointUpdateRequest request1 = new UserPointUpdateRequest(1L, 10, false);

        //when
        user.updatePoint(user.getPointAmount()-request1.getUpdateAmount());

        //then
        Assertions.assertThat(user.getPointAmount()).isEqualTo(90);
    }
}