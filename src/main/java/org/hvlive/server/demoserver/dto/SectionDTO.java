package org.hvlive.server.demoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hvlive.server.demoserver.entity.Section;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {
    private Long id;
    private Long channelId;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static SectionDTO fromEntity(Section section) {
        return SectionDTO.builder()
                .id(section.getId())
                .channelId(section.getChannelId())
                .name(section.getName())
                .createTime(section.getCreateTime())
                .updateTime(section.getUpdateTime())
                .build();
    }

    public static List<SectionDTO> fromEntities(List<Section> sections) {
        return sections.stream().map(SectionDTO::fromEntity).collect(Collectors.toList());
    }
}
