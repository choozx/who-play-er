package com.yjh.whoplayer.service;

import com.yjh.whoplayer.model.EternalReturnDto.PlayerListRes.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.StringUtil;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.util.stream.Collectors;

/**
 * Created by dale on 2025-01-04.
 */

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class EternalReturnAPIServiceTest {

    private final EternalReturnAPIService eternalReturnAPIService;

    @Test
    public void 유저_정보() {
        String nickname = "쌍문동곡갱이";
        var userInfo = eternalReturnAPIService.getUserInfo(nickname);

        log.info("{}", userInfo);
    }

    @Test
    public void 유저_게임_정보() throws InterruptedException {
        String nickname = "쌍문동곡갱이";
        var userInfo = eternalReturnAPIService.getUserInfo(nickname);

        log.info("{}", userInfo);

        Thread.sleep(2000); // api가 1초에 한번 밖에 못보냄

        long userNum = userInfo.getUser().getUserNum();
        var gameList = eternalReturnAPIService.getGameInfo(userNum);

        for (var game : gameList) {
            log.info("게임 아이디 : {}", game.getGameId());
        }
    }

    @Test
    public void 게임_참여자_정보() throws InterruptedException {
        String nickname = "쌍문동곡갱이";
        var userInfo = eternalReturnAPIService.getUserInfo(nickname);

        log.info("{}", userInfo);

        Thread.sleep(2000); // api가 1초에 한번 밖에 못보냄

        long userNum = userInfo.getUser().getUserNum();
        var gameList = eternalReturnAPIService.getGameInfo(userNum);

        for (var game : gameList) {
            var playerList = eternalReturnAPIService.getPlayerInfo(game.getGameId());

            String playerString = playerList.stream().map(Player::getNickname)
                            .collect(Collectors.joining("|"));
            log.info("게임 아이디:{} , 참여자:{}", game.getGameId(), playerString);

            Thread.sleep(1500); // api가 1초에 한번 밖에 못보냄
        }
    }
}