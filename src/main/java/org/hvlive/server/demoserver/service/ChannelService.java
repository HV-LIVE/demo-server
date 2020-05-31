package org.hvlive.server.demoserver.service;

import org.hvlive.server.demoserver.dto.ChannelDTO;
import org.hvlive.server.demoserver.entity.Channel;
import org.hvlive.server.demoserver.exception.BadRequestException;
import org.hvlive.server.demoserver.repository.ChannelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChannelService {
    private ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public List<ChannelDTO> getAllChannels() {
        return ChannelDTO.fromEntities(channelRepository.findAll());
    }

    @Transactional
    public ChannelDTO createChannel(ChannelDTO channelDTO) {
        if (channelRepository.existsByName(channelDTO.getName())) {
            throw new BadRequestException();
        }
        Channel channel = Channel.builder()
                .name(channelDTO.getName())
                .build();
        channelRepository.saveAndFlush(channel);
        return ChannelDTO.fromEntity(channel);
    }

    @Transactional
    public ChannelDTO updateChannel(ChannelDTO channelDTO) {
        if (channelRepository.existsByIdNotAndName(channelDTO.getId(), channelDTO.getName())) {
            throw new BadRequestException();
        }
        Channel channel = Channel.builder()
                .id(channelDTO.getId())
                .name(channelDTO.getName())
                .build();
        channelRepository.saveAndFlush(channel);
        return ChannelDTO.fromEntity(channel);
    }

    @Transactional
    public void deleteChannel(Long channelId) {
        channelRepository.deleteById(channelId);
    }
}
