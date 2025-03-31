package com.yjh.whoplayer.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by dale on 2025-01-04.
 */

@Getter
@Builder
public class UserDto {
    private long uid;
    private long erUid;
    private String nickname;
}
