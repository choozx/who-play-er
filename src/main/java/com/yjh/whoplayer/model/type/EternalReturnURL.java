package com.yjh.whoplayer.model.type;

import lombok.Getter;

/**
 * Created by dale on 2025-01-04.
 */
public enum EternalReturnURL {

    BASE("https://open-api.bser.io"),
    DATA("/v2/data/"),
    USER_INFO("/v1/user/nickname"),
    USER_GAME("/v1/user/games"),
    PLAYER_INFO("/v1/games"),
    ;

    @Getter
    private String url;

    EternalReturnURL(String url) {
        this.url = url;
    }
}
