package com.yjh.whoplayer.controller.data;

import com.yjh.whoplayer.model.GameCelebInfo.GameCelebInfoRes;
import com.yjh.whoplayer.model.UserRes;
import com.yjh.whoplayer.service.GameService;
import com.yjh.whoplayer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final GameService gameService;

    @GetMapping("/user")
    public void getUserInfo(@RequestParam String nickname, @RequestParam int page) {
        UserRes userRes = userService.getUserInfo(nickname);
        List<GameCelebInfoRes> gameCelebInfoList = gameService.getGameCelebInfoList(userRes.getErUid(), page);
    }

    @PostMapping("/update-game")
    public void updateGame(@RequestBody Long erUid) {
        gameService.updateGame(erUid);
    }
}
