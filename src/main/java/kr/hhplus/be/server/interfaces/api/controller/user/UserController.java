package kr.hhplus.be.server.interfaces.api.controller.user;


import kr.hhplus.be.server.domain.user.UserService;
import kr.hhplus.be.server.interfaces.dto.user.request.UserPointUpdateRequest;
import kr.hhplus.be.server.interfaces.dto.user.response.UserPointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    //(포인트) 잔액 조회
    @GetMapping("/point/{userId}")
    public long getCurrentPointAmount(@PathVariable Long userId){
        UserPointResponse response = new UserPointResponse(userId);
        return userService.getCurrentPointAmount(response);
    }

    //(포인트) 충전
    @PostMapping("/point/charge")
    public void updatePointAmountById(@RequestBody UserPointUpdateRequest request){
        userService.updatePointAmountById(request);
    }
}
