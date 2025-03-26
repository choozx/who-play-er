package com.yjh.whoplayer.entity;

import com.yjh.whoplayer.model.EternalReturnAPIDto.UserInfoRes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

@Entity
@Getter
@Table(name = "user")
public class UserEntity implements Persistable<Long> {

    @Id
    @Column(name = "uid")
    private long uid;
    @Column(name = "er_uid")
    private long er_uid;
    @Column(name = "er_nickname")
    private String erNickname;

    public static UserEntity createNew(UserInfoRes res) {
        UserEntity entity = new UserEntity();
        entity.er_uid = res.getUser().getUserNum();
        entity.erNickname = res.getUser().getNickname();
        return entity;
    }

    @Override
    public Long getId() {
        return uid;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
