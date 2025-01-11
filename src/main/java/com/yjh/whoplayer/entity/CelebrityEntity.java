package com.yjh.whoplayer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

/**
 * Created by dale on 2025-01-04.
 */

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "celeb")
public class CelebrityEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "user_num")
    private long userNum;
    @Column(name = "er_nickname")
    private String erNickname;
    @Column(name = "streamer_name")
    private String streamerName;

    public static CelebrityEntity newEntity(long userNum, String erNickname, String nickname) {
        CelebrityEntity celebrityEntity = new CelebrityEntity();
        celebrityEntity.userNum = userNum;
        celebrityEntity.erNickname = erNickname;
        celebrityEntity.streamerName = nickname;

        return celebrityEntity;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
