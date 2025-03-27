package com.yjh.whoplayer.model;

import com.yjh.whoplayer.model.cache.CacheCelebrity.Celebrity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class GameCelebInfo {

    @Builder
    public static class GameCelebInfoRes {
        private long gameId;
        private List<PlayerInfo> playerInfoList;
    }

    @Getter
    @Builder
    public static class PlayerInfo {
        private long erUid;
        private String nickname;
        private boolean isCeleb;
        private String streamerName;

        public void setCeleb(Celebrity celebrity) {
            isCeleb = true;
            streamerName = celebrity.getStreamerName();
        }
    }
}
