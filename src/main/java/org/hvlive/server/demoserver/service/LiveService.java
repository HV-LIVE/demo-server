package org.hvlive.server.demoserver.service;

import org.hvlive.server.demoserver.dto.LiveDTO;
import org.hvlive.server.demoserver.entity.Live;
import org.hvlive.server.demoserver.exception.BadRequestException;
import org.hvlive.server.demoserver.exception.Errors;
import org.hvlive.server.demoserver.repository.LiveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LiveService {
    private LiveRepository liveRepository;
    private ChannelService channelService;
    private SectionService sectionService;
    private UserService userService;

    public LiveService(LiveRepository liveRepository, ChannelService channelService, SectionService sectionService, UserService userService) {
        this.liveRepository = liveRepository;
        this.channelService = channelService;
        this.sectionService = sectionService;
        this.userService = userService;
    }

    public List<LiveDTO> getAllLives() {
        List<Live> lives = liveRepository.findAll();
        Set<Long> channelIds = new HashSet<>();
        Set<Long> sectionIds = new HashSet<>();
        Set<Long> userIds = new HashSet<>();
        lives.forEach(live -> {
            channelIds.add(live.getChannelId());
            sectionIds.add(live.getSectionId());
            userIds.add(live.getUserId());
        });
        return LiveDTO.fromEntities(lives, channelService.getChannels(channelIds), sectionService.getSections(sectionIds), userService.getUsers(userIds));
    }

    public List<LiveDTO> getLives(Long userId) {
        List<Live> lives = liveRepository.findAllByUserId(userId);
        Set<Long> channelIds = new HashSet<>();
        Set<Long> sectionIds = new HashSet<>();
        Set<Long> userIds = new HashSet<>();
        lives.forEach(live -> {
            channelIds.add(live.getChannelId());
            sectionIds.add(live.getSectionId());
            userIds.add(live.getUserId());
        });
        return LiveDTO.fromEntities(lives, channelService.getChannels(channelIds), sectionService.getSections(sectionIds), userService.getUsers(userIds));
    }

    public List<LiveDTO> getAvailableLives() {
        LocalDateTime now = LocalDateTime.now();
        List<Live> lives = liveRepository.findAllByStartTimeLessThanAndEndTimeGreaterThan(now, now);
        Set<Long> channelIds = new HashSet<>();
        Set<Long> sectionIds = new HashSet<>();
        Set<Long> userIds = new HashSet<>();
        lives.forEach(live -> {
            channelIds.add(live.getChannelId());
            sectionIds.add(live.getSectionId());
            userIds.add(live.getUserId());
        });
        return LiveDTO.fromEntities(lives, channelService.getChannels(channelIds), sectionService.getSections(sectionIds), userService.getUsers(userIds))
                .stream()
                .filter(liveDTO -> liveDTO.getChannel() != null && liveDTO.getSection() != null && liveDTO.getUser() != null)
                .collect(Collectors.toList());
    }

    @Transactional
    public LiveDTO createLive(LiveDTO liveDTO) {
        if (liveRepository.existsByUserIdAndStartTimeBetween(liveDTO.getUserId(), liveDTO.getStartTime(), liveDTO.getEndTime())) {
            throw new BadRequestException(Errors.Live.CODE_TIME_CONFLICT, Errors.Live.MESSAGE_TIME_CONFLICT);
        }
        if (liveRepository.existsByUserIdAndEndTimeBetween(liveDTO.getUserId(), liveDTO.getStartTime(), liveDTO.getEndTime())) {
            throw new BadRequestException(Errors.Live.CODE_TIME_CONFLICT, Errors.Live.MESSAGE_TIME_CONFLICT);
        }
        Live live = Live.builder()
                .title(liveDTO.getTitle())
                .channelId(liveDTO.getChannelId())
                .sectionId(liveDTO.getSectionId())
                .userId(liveDTO.getUserId())
                .startTime(liveDTO.getStartTime())
                .endTime(liveDTO.getEndTime())
                .build();
        liveRepository.saveAndFlush(live);
        return LiveDTO.fromEntity(live, channelService.getChannel(live.getChannelId()), sectionService.getSection(live.getSectionId()), userService.getUser(live.getUserId()));
    }

    @Transactional
    public LiveDTO updateLive(LiveDTO liveDTO) {
        if (liveRepository.existsByIdNotAndUserIdAndStartTimeBetween(liveDTO.getId(), liveDTO.getUserId(), liveDTO.getStartTime(), liveDTO.getEndTime())) {
            throw new BadRequestException(Errors.Live.CODE_TIME_CONFLICT, Errors.Live.MESSAGE_TIME_CONFLICT);
        }
        if (liveRepository.existsByIdNotAndUserIdAndEndTimeBetween(liveDTO.getId(), liveDTO.getUserId(), liveDTO.getStartTime(), liveDTO.getEndTime())) {
            throw new BadRequestException(Errors.Live.CODE_TIME_CONFLICT, Errors.Live.MESSAGE_TIME_CONFLICT);
        }
        Live live = Live.builder()
                .id(liveDTO.getId())
                .title(liveDTO.getTitle())
                .channelId(liveDTO.getChannelId())
                .sectionId(liveDTO.getSectionId())
                .userId(liveDTO.getUserId())
                .startTime(liveDTO.getStartTime())
                .endTime(liveDTO.getEndTime())
                .build();
        liveRepository.saveAndFlush(live);
        return LiveDTO.fromEntity(live, channelService.getChannel(live.getChannelId()), sectionService.getSection(live.getSectionId()), userService.getUser(live.getUserId()));
    }

    @Transactional
    public void deleteLive(Long liveId) {
        liveRepository.deleteById(liveId);
    }
}
