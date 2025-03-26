package com.yjh.whoplayer.service;

import com.yjh.whoplayer.model.EternalReturnAPIDto.GameInfo;
import com.yjh.whoplayer.repository.GameInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final UserService userService;
    private final EternalReturnAPIService eternalReturnAPIService;

    private final GameInfoRepository gameInfoRepository;

    public void getGameInfoList(int erUid) {

    }

    public void updateGame(long erUid) {
        List<GameInfo> gameInfoList = eternalReturnAPIService.getGameInfo(erUid);

        // TODO DB 저장
        //  새로운 게임 응답값 생성
    }
}
