package com.yjh.whoplayer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

/**
 * Created by dale on 2025-01-04.
 */

@Entity
@Getter
@Table(name = "player")
public class GameInfoEntity implements Persistable<Long> {

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "user_num")
    private long userNum;
    @Column(name = "game_id")
    private long gameId;
    @Column(name = "nickname")
    private String nickname;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
