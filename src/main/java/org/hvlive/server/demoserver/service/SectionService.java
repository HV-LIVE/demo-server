package org.hvlive.server.demoserver.service;

import org.hvlive.server.demoserver.dto.SectionDTO;
import org.hvlive.server.demoserver.dto.UserDTO;
import org.hvlive.server.demoserver.entity.Section;
import org.hvlive.server.demoserver.exception.BadRequestException;
import org.hvlive.server.demoserver.repository.SectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SectionService {
    private SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<SectionDTO> getSections(Long channelId) {
        return SectionDTO.fromEntities(sectionRepository.findAllByChannelId(channelId));
    }

    public Map<Long, SectionDTO> getSections(Set<Long> ids) {
        Map<Long, SectionDTO> sections = new HashMap<>();
        sectionRepository.findAllByIdIn(ids).forEach(section -> sections.put(section.getId(), SectionDTO.fromEntity(section)));
        return sections;
    }

    public Map<Long, List<SectionDTO>> getSectionsGroupByChannelId() {
        Map<Long, List<SectionDTO>> result = new HashMap<>();
        sectionRepository.findAll().forEach((section -> {
            List<SectionDTO> sections = result.computeIfAbsent(section.getChannelId(), k -> new ArrayList<>());
            sections.add(SectionDTO.fromEntity(section));
        }));
        return result;
    }

    public SectionDTO getSection(Long id) {
        return sectionRepository.findById(id).map(SectionDTO::fromEntity).orElse(null);
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
