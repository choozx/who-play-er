package com.yjh.whoplayer.service;

import com.yjh.whoplayer.entity.GameInfoEntity;
import com.yjh.whoplayer.model.EternalReturnAPIDto.GameInfoRes;
import com.yjh.whoplayer.model.GameCelebInfo.PlayerInfo;
import com.yjh.whoplayer.model.GameCelebInfo.GameCelebInfoRes;
import com.yjh.whoplayer.model.GameInfoDto;
import com.yjh.whoplayer.model.cache.CacheCelebrity;
import com.yjh.whoplayer.model.cache.CacheCelebrity.Celebrity;
import com.yjh.whoplayer.repository.GameInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.yjh.whoplayer.model.constant.GlobalConst.GAME_PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class GameService {

    private final EternalReturnAPIService eternalReturnAPIService;

    private final GameInfoRepository gameInfoRepository;

    public List<GameCelebInfoRes> getGameCelebInfoList(long erUid, int page) {
        List<GameInfoDto> gameInfoDtoList = getGameInfoDtoList(erUid, page);

        CacheCelebrity cacheCelebrity = CacheDataService.getDic(CacheCelebrity.class);
        List<GameCelebInfoRes> resList = new ArrayList<>();
        for (GameInfoDto gameInfoDto : gameInfoDtoList) {
            List<PlayerInfo> playerIdList = gameInfoDto.getPlayerInfoList();
            for (PlayerInfo playerInfo : playerIdList) {
                if (cacheCelebrity.isCelebErUid(playerInfo.getErUid())) {
                    Celebrity celebrity = cacheCelebrity.getCeleb(playerInfo.getErUid());
                    playerInfo.setCeleb(celebrity);
                }
            }

            GameCelebInfoRes res = GameCelebInfoRes.builder()
                    .gameId(gameInfoDto.getGameId())
                    .playerInfoList(gameInfoDto.getPlayerInfoList())
                    .build();
            resList.add(res);
        }

        return resList;
    }

    private List<GameInfoDto> getGameInfoDtoList(long erUid, int page) {
        Pageable pageable = PageRequest.of(page, GAME_PAGE_SIZE);

        // 페이지네이션된 게임 ID 목록 조회
        List<Long> gameIds = gameInfoRepository
                .findByErUidOrderByGameIdDesc(erUid, pageable)
                .getContent()
                .stream()
                .map(GameInfoEntity::getGameId)
                .toList();

        // 해당 게임 ID에 속하는 모든 게임 정보 조회
        return gameInfoRepository.findByGameIdIn(gameIds).stream()
                .collect(Collectors.groupingBy(
                        GameInfoEntity::getGameId,
                        Collectors.mapping(
                                e -> PlayerInfo.builder().erUid(e.getErUid()).nickname(e.getNickname()).build(),
                                Collectors.toList()
                        )
                ))
                .entrySet().stream()
                .map(entry -> new GameInfoDto(entry.getKey(), entry.getValue()))
                .toList();
    }


    public void updateGame(long erUid) {
        List<GameInfoRes> gameInfoResList = eternalReturnAPIService.getGameInfo(erUid);

        // TODO DB 저장
        //  새로운 게임 응답값 생성
    }
}
