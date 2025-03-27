package com.yjh.whoplayer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GameInfoDto {
    private long gameId;
    private List<GameCelebInfo.PlayerInfo> playerInfoList;
}
