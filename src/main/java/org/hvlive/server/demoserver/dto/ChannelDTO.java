package org.hvlive.server.demoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hvlive.server.demoserver.entity.Channel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDTO {
    private Long id;
    private String name;
    private List<SectionDTO> sections;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static ChannelDTO fromEntity(Channel channel, List<SectionDTO> sections) {
        return ChannelDTO.builder()
                .id(channel.getId())
                .name(channel.getName())
                .sections(sections)
                .createTime(channel.getCreateTime())
                .updateTime(channel.getUpdateTime())
                .build();
    }

    public static List<ChannelDTO> fromEntities(List<Channel> channels, Map<Long, List<SectionDTO>> sections) {
        return channels.stream()
                .map((channel) -> ChannelDTO.fromEntity(channel, sections.get(channel.getId())))
                .collect(Collectors.toList());
    }
}
