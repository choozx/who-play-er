package com.yjh.whoplayer.model;

import lombok.Builder;

import java.util.List;

@Builder
public class UserRes {
    private UserDto user;
    private List<GameCelebInfo.GameCelebInfoRes> gameCelebInfoList;
}
