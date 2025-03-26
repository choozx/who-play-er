package com.yjh.whoplayer.service;

import com.yjh.whoplayer.repository.GameInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameInfoRepository gameInfoRepository;

    public void getGameInfoList(int erUid) {

    }

    public void updateGame(int erUid) {

    }
}
