package kr.hhplus.be.server.domain.user;

import kr.hhplus.be.server.infrastructure.user.UserJpaRepository;
import kr.hhplus.be.server.interfaces.dto.user.request.UserPointUpdateRequest;
import kr.hhplus.be.server.interfaces.dto.user.response.UserPointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJpaRepository userJpaRepository;

    //유저 포인트 조회 //
    public long getCurrentPointAmount(UserPointResponse response){
        //유저 존재 확인
        User user = userJpaRepository.findById(response.getUserId())
                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않습니다."));
        //존재하면 해당 유저의 포인트 return
        return user.getPointAmount();
    }

    //유저 포인트 추가 감소
    public void updatePointAmountById(UserPointUpdateRequest request) {
        //1.유저 존재 확인
        User user = userJpaRepository.findById(request.getId())
                .orElseThrow(()->new IllegalArgumentException("유저가 존재하지 않습니다."));

        //2.dto의 updateAmount와 currentPoint를 비교하여 정책에 어긋나면 Exception
        long currentPoint = user.getPointAmount();

        //포인트 추가인지 감소인지
        if(request.isPointState()){
            currentPoint += request.getUpdateAmount();
        }else{
            currentPoint -= request.getUpdateAmount();
        }

        //포인트 예외상황
        if(currentPoint < 0){
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        if(currentPoint > 800000000000000L){
            throw new IllegalArgumentException("최대 충전 금액을 넘어섰습니다.");
        }

        //3.dto의 updateAmount를 현재 유저에 추가/감소한다.
        user.updatePoint(currentPoint);

        //4.저장한다.
        userJpaRepository.save(user);
    }
}
