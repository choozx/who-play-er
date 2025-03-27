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
@Table(name = "game_info")
public class GameInfoEntity implements Persistable<Long> {

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "er_uid")
    private long erUid;
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
