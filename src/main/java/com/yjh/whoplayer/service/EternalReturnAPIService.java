package com.yjh.whoplayer.service;

import com.yjh.whoplayer.model.EternalReturnDto.PlayerListRes;
import com.yjh.whoplayer.model.EternalReturnDto.PlayerListRes.Player;
import com.yjh.whoplayer.model.EternalReturnDto.GameListRes;
import com.yjh.whoplayer.model.EternalReturnDto.GameListRes.Game;
import com.yjh.whoplayer.model.EternalReturnDto.UserInfoRes;
import com.yjh.whoplayer.model.type.EternalReturnURL;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * Created by dale on 2025-01-04.
 */

@Service
@RequiredArgsConstructor
public class EternalReturnAPIService {

    private final RestClient restClient;

    public UserInfoRes getUserInfo(String nickname) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(EternalReturnURL.USER_INFO.getUrl())
                        .queryParam("query", nickname)
                        .build())
                .retrieve()
                .body(UserInfoRes.class);
    }

    public List<Game> getGameInfo(long userNum) {
        var gameListRes = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(EternalReturnURL.USER_GAME.getUrl() + "/" + userNum)
                        .build())
                .retrieve()
                .body(GameListRes.class);
        return gameListRes.getGameList();
    }

    public List<Player> getPlayerInfo(int gameId) {
        var playerListRes = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(EternalReturnURL.PLAYER_INFO.getUrl() + "/" + gameId)
                        .build())
                .retrieve()
                .body(PlayerListRes.class);
        return playerListRes.getPlayerList();
    }
}
