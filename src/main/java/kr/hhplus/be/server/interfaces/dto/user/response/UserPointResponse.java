package kr.hhplus.be.server.interfaces.dto.user.response;

import lombok.Getter;

@Getter
public class UserPointResponse {

    private Long userId;
    private long pointAmount;

    public UserPointResponse(Long userId){
        this.userId = userId;
    }
}
