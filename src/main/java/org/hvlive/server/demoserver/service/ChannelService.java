package org.hvlive.server.demoserver.service;

import org.hvlive.server.demoserver.dto.ChannelDTO;
import org.hvlive.server.demoserver.entity.Channel;
import org.hvlive.server.demoserver.exception.BadRequestException;
import org.hvlive.server.demoserver.exception.Errors;
import org.hvlive.server.demoserver.repository.ChannelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChannelService {
    private ChannelRepository channelRepository;
    private SectionService sectionService;

    public ChannelService(ChannelRepository channelRepository, SectionService sectionService) {
        this.channelRepository = channelRepository;
        this.sectionService = sectionService;
    }

    public List<ChannelDTO> getAllChannels() {
        return ChannelDTO.fromEntities(channelRepository.findAll(), Collections.emptyMap());
    }

    public List<ChannelDTO> getAllChannelsAndSections() {
        return ChannelDTO.fromEntities(channelRepository.findAll(), sectionService.getSectionsGroupByChannelId());
    }

    public List<ChannelDTO> getAvailableChannels() {
        return getAllChannelsAndSections().stream()
                .filter(channelDTO -> channelDTO.getSections() != null && !channelDTO.getSections().isEmpty())
                .collect(Collectors.toList());
    }

    public Map<Long, ChannelDTO> getChannels(Set<Long> ids) {
        Map<Long, ChannelDTO> channels = new HashMap<>();
        channelRepository.findAllByIdIn(ids).forEach(channel -> channels.put(channel.getId(), ChannelDTO.fromEntity(channel, null)));
        return channels;
    }

    public ChannelDTO getChannel(Long id) {
        return channelRepository.findById(id).map(channel -> ChannelDTO.fromEntity(channel, null)).orElse(null);
    }

    @Transactional
    public ChannelDTO createChannel(ChannelDTO channelDTO) {
        if (channelRepository.existsByName(channelDTO.getName())) {
            throw new BadRequestException(Errors.Channel.CODE_NAME_EXISTS, Errors.Channel.MESSAGE_NAME_EXISTS);
        }
        Channel channel = Channel.builder()
                .name(channelDTO.getName())
                .build();
        channelRepository.saveAndFlush(channel);
        return ChannelDTO.fromEntity(channel, null);
    }

    @Transactional
    public ChannelDTO updateChannel(ChannelDTO channelDTO) {
        if (channelRepository.existsByIdNotAndName(channelDTO.getId(), channelDTO.getName())) {
            throw new BadRequestException(Errors.Channel.CODE_NAME_EXISTS, Errors.Channel.MESSAGE_NAME_EXISTS);
        }
        Channel channel = Channel.builder()
                .id(channelDTO.getId())
                .name(channelDTO.getName())
                .build();
        channelRepository.saveAndFlush(channel);
        return ChannelDTO.fromEntity(channel, null);
    }

    @Transactional
    public void deleteChannel(Long channelId) {
        channelRepository.deleteById(channelId);
    }
}
