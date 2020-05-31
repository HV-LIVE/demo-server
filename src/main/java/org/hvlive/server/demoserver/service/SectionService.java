package org.hvlive.server.demoserver.service;

import org.hvlive.server.demoserver.dto.SectionDTO;
import org.hvlive.server.demoserver.entity.Section;
import org.hvlive.server.demoserver.exception.BadRequestException;
import org.hvlive.server.demoserver.repository.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SectionService {
    private SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<SectionDTO> getSections(Long channelId) {
        return SectionDTO.fromEntities(sectionRepository.findAllByChannelId(channelId));
    }

    @Transactional
    public SectionDTO createSection(SectionDTO sectionDTO) {
        if (sectionRepository.existsByChannelIdAndName(sectionDTO.getChannelId(), sectionDTO.getName())) {
            throw new BadRequestException();
        }
        Section channel = Section.builder()
                .channelId(sectionDTO.getChannelId())
                .name(sectionDTO.getName())
                .build();
        sectionRepository.saveAndFlush(channel);
        return SectionDTO.fromEntity(channel);
    }

    @Transactional
    public SectionDTO updateSection(SectionDTO sectionDTO) {
        if (sectionRepository.existsByIdNotAndChannelIdAndName(sectionDTO.getId(), sectionDTO.getChannelId(), sectionDTO.getName())) {
            throw new BadRequestException();
        }
        Section channel = Section.builder()
                .id(sectionDTO.getId())
                .channelId(sectionDTO.getChannelId())
                .name(sectionDTO.getName())
                .build();
        sectionRepository.saveAndFlush(channel);
        return SectionDTO.fromEntity(channel);
    }

    @Transactional
    public void deleteSection(Long sectionId) {
        sectionRepository.deleteById(sectionId);
    }
}
