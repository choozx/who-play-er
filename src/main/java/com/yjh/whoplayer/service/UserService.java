package com.yjh.whoplayer.service;

import com.yjh.whoplayer.entity.UserEntity;
import com.yjh.whoplayer.model.EternalReturnAPIDto.UserInfoRes;
import com.yjh.whoplayer.model.UserDto;
import com.yjh.whoplayer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final EternalReturnAPIService eternalReturnAPIService;

    private final UserRepository userRepository;

    public UserDto getUserInfo(String nickname) {
        UserEntity userEntity = getUserEntity(nickname);

        return UserDto.builder()
                .uid(userEntity.getUid())
                .erUid(userEntity.getErUid())
                .nickname(userEntity.getErNickname())
                .build();
    }

    public void updateUser(long erUid) {
        // TODO 티어나 유저 정보에 관한거 업데이트
    }

    private UserEntity getUserEntity(String nickname) {
        UserEntity userEntity;
        Optional<UserEntity> optionalUserEntity = userRepository.findByErNickname(nickname);
        userEntity = optionalUserEntity.orElseGet(() -> createNewEntity(nickname));

        return userEntity;
    }

    private UserEntity createNewEntity(String nickname) {
        UserInfoRes userInfoRes = eternalReturnAPIService.getUserInfo(nickname);
        return UserEntity.createNew(userInfoRes);
    }
}
