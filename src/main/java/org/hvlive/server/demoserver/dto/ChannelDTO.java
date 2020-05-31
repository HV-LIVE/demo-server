package org.hvlive.server.demoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hvlive.server.demoserver.entity.Channel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDTO {
    private Long id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static ChannelDTO fromEntity(Channel channel) {
        return ChannelDTO.builder()
                .id(channel.getId())
                .name(channel.getName())
                .createTime(channel.getCreateTime())
                .updateTime(channel.getUpdateTime())
                .build();
    }

    public static List<ChannelDTO> fromEntities(List<Channel> channels) {
        return channels.stream().map(ChannelDTO::fromEntity).collect(Collectors.toList());
    }
}
