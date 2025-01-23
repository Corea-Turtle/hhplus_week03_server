package kr.hhplus.be.server.interfaces.dto.user.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class UserPointUpdateRequest {

    private Long id;
    private int updateAmount;
    private boolean pointState; //true이면 증가 false면 감소;

    @Builder
    public UserPointUpdateRequest(Long Id, int updateAmount, boolean pointState){
        this.id = id;
        this.updateAmount = updateAmount;
        this.pointState = pointState;
    }
}
