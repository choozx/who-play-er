package com.yjh.whoplayer.service;

import com.yjh.whoplayer.model.EternalReturnDto.GameInfo;
import com.yjh.whoplayer.model.EternalReturnDto.GameInfoListRes;
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

    public List<GameInfo> getGameInfo(long userNum) {
        var gameListRes = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(EternalReturnURL.USER_GAME.getUrl() + "/" + userNum)
                        .build())
                .retrieve()
                .body(GameInfoListRes.class);
        return gameListRes.getGameInfoList();
    }

    public List<GameInfo> getPlayerInfo(int gameId) {
        var gameInfoRes = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(EternalReturnURL.PLAYER_INFO.getUrl() + "/" + gameId)
                        .build())
                .retrieve()
                .body(GameInfoListRes.class);
        return gameInfoRes.getGameInfoList();
    }
}
