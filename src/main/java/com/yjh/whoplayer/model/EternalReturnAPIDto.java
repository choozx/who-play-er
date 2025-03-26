package com.yjh.whoplayer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by dale on 2025-01-04.
 */
public class EternalReturnAPIDto {

    @Data
    public static class UserInfoRes {
        private int code;
        private String message;
        private User user;
    }

    @Data
    public static class GameInfoListRes {
        private int code;
        private String message;
        @JsonProperty("userGames")
        private List<GameInfo> gameInfoList;
    }

    @Data
    public static class GameInfo {
        private int gameId;
        private long userNum;
        private String nickname;
    }

    @Data
    public static class User {
        private long userNum;
        private String nickname;
    }
}
