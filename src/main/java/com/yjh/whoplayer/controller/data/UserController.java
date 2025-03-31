package com.yjh.whoplayer.controller.data;

import com.yjh.whoplayer.model.GameCelebInfo.GameCelebInfoRes;
import com.yjh.whoplayer.model.UserDto;
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
    public UserRes getUserInfo(@RequestParam String nickname, @RequestParam int page) {
        UserDto userDto = userService.getUserInfo(nickname);
        List<GameCelebInfoRes> gameCelebInfoList = gameService.getGameCelebInfoList(userDto.getErUid(), page);

        return UserRes.builder()
                .user(userDto)
                .gameCelebInfoList(gameCelebInfoList)
                .build();
    }

    @PostMapping("/update-game")
    public void updateGame(@RequestBody Long erUid) {
        userService.updateUser(erUid);
        gameService.updateGame(erUid);
    }
}
