package com.yjh.whoplayer.controller.data;

import com.yjh.whoplayer.model.UserRes;
import com.yjh.whoplayer.service.GameService;
import com.yjh.whoplayer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final GameService gameService;

    @GetMapping("/user")
    public void getUserInfo(@RequestParam String nickname) {
        // TODO 유저 정보 가져오기
        UserRes userRes = userService.getUserInfo(nickname);
        // TODO 유저 매칭 정보 가져오기
    }

    @PostMapping("/update-game")
    public void updateGame(@RequestBody Integer erUid) {
        gameService.updateGame(erUid);
    }
}
