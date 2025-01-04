package com.yjh.whoplayer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by dale on 2025-01-04.
 */
public class EternalReturnDto {

    @Data
    public static class UserInfoRes {
        private int code;
        private String message;
        private User user;

        @Data
        public static class User {
            private long userNum;
            private String nickname;
        }
    }

    @Data
    public static class GameListRes {
        private int code;
        private String message;
        @JsonProperty("userGames")
        private List<Game> gameList;

        @Data
        public static class Game {
            private int gameId;
        }
    }

    @Data
    public static class PlayerListRes {
        private int code;
        private String message;
        @JsonProperty("userGames")
        private List<Player> playerList;

        @Data
        public static class Player {
            private long userNum;
            private String nickname;
        }
    }
}
