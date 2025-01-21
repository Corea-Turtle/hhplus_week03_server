package kr.hhplus.be.server.domain.user;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.infrastructure.user.UserJpaRepository;
import kr.hhplus.be.server.interfaces.dto.user.request.UserPointUpdateRequest;
import kr.hhplus.be.server.interfaces.dto.user.response.UserPointResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserJpaRepository userJpaRepository;

    @InjectMocks
    UserService userService;

    @DisplayName("유저아이디를 입력하여 해당 아이디의 현재 포인트를 조회한다.")
    @Test
    void getCurrentPointAmount() {
        //given
        User user = new User( "김유저", 10);
        Mockito.when(userJpaRepository.findById(user.getId())).thenReturn(Optional.of(user));

        UserPointResponse response = new UserPointResponse(user.getId());

        //when
        User userFound = userJpaRepository.findById(user.getId())
                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않습니다."));

        //then
        Assertions.assertThat(userService.getCurrentPointAmount(response)).isEqualTo(10);
    }

    @DisplayName("유저아이디를 조회한 후 해당 유저 아이디의 포인트를 더한 값으로 업데이트한다.")
    @Test
    void updatePointPositiveAmountById() {
        //given
        User user = new User("이유저", 10);
        Mockito.when(userJpaRepository.findById(user.getId())).thenReturn(Optional.of(user));

        UserPointUpdateRequest request = new UserPointUpdateRequest(user.getId(), 100, true);

        //when
        User userFound = userJpaRepository.findById(user.getId())
                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않습니다."));

        userService.updatePointAmountById(request);

        Assertions.assertThat(userFound.getPointAmount()).isEqualTo(110);
        //then
    }

    @DisplayName("유저아이디를 조회한 후 해당 유저 아이디의 포인트를 뺸 값으로 업데이트한다.")
    @Test
    void updatePointNegativeAmountById() {
        //given
        User user = new User("이유저", 100);
        Mockito.when(userJpaRepository.findById(user.getId())).thenReturn(Optional.of(user));

        UserPointUpdateRequest request = new UserPointUpdateRequest(user.getId(), 90, false);

        //when
        User userFound = userJpaRepository.findById(user.getId())
                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않습니다."));

        userService.updatePointAmountById(request);

        Assertions.assertThat(userFound.getPointAmount()).isEqualTo(10);
        //then
    }
}