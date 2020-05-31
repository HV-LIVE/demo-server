package org.hvlive.server.demoserver.service;

import org.hvlive.server.demoserver.dto.LiveDTO;
import org.hvlive.server.demoserver.entity.Live;
import org.hvlive.server.demoserver.exception.BadRequestException;
import org.hvlive.server.demoserver.repository.LiveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LiveService {
    private LiveRepository liveRepository;
    private UserService userService;

    public LiveService(LiveRepository liveRepository, UserService userService) {
        this.liveRepository = liveRepository;
        this.userService = userService;
    }

    public List<LiveDTO> getLives(Long sectionId) {
        Set<Long> userIds = new HashSet<>();
        List<Live> lives = liveRepository.findAllBySectionId(sectionId);
        lives.forEach(live -> userIds.add(live.getId()));
        return LiveDTO.fromEntities(lives, userService.getUsers(userIds));
    }

    @Transactional
    public LiveDTO createLive(LiveDTO liveDTO) {
        if (liveRepository.existsByUserIdAndStartTimeBetween(liveDTO.getUserId(), liveDTO.getStartTime(), liveDTO.getEndTime())) {
            throw new BadRequestException();
        }
        if (liveRepository.existsByUserIdAndEndTimeBetween(liveDTO.getUserId(), liveDTO.getStartTime(), liveDTO.getEndTime())) {
            throw new BadRequestException();
        }
        Live live = Live.builder()
                .title(liveDTO.getTitle())
                .sectionId(liveDTO.getSectionId())
                .userId(liveDTO.getUserId())
                .startTime(liveDTO.getStartTime())
                .endTime(liveDTO.getEndTime())
                .build();
        liveRepository.saveAndFlush(live);
        return LiveDTO.fromEntity(live, userService.getUser(live.getUserId()));
    }

    @Transactional
    public LiveDTO updateLive(LiveDTO liveDTO) {
        if (liveRepository.existsByIdNotAndUserIdAndStartTimeBetween(liveDTO.getId(), liveDTO.getUserId(), liveDTO.getStartTime(), liveDTO.getEndTime())) {
            throw new BadRequestException();
        }
        if (liveRepository.existsByIdNotAndUserIdAndEndTimeBetween(liveDTO.getId(), liveDTO.getUserId(), liveDTO.getStartTime(), liveDTO.getEndTime())) {
            throw new BadRequestException();
        }
        Live live = Live.builder()
                .id(liveDTO.getId())
                .title(liveDTO.getTitle())
                .sectionId(liveDTO.getSectionId())
                .userId(liveDTO.getUserId())
                .startTime(liveDTO.getStartTime())
                .endTime(liveDTO.getEndTime())
                .build();
        liveRepository.saveAndFlush(live);
        return LiveDTO.fromEntity(live, userService.getUser(live.getUserId()));
    }

    @Transactional
    public void deleteLive(Long liveId) {
        liveRepository.deleteById(liveId);
    }
}
