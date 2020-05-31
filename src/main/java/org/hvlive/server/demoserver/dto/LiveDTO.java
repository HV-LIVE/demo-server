package org.hvlive.server.demoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hvlive.server.demoserver.entity.Live;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LiveDTO {
    private Long id;
    private String title;
    private Long sectionId;
    private Long userId;
    private UserDTO user;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static LiveDTO fromEntity(Live live, UserDTO user) {
        return LiveDTO.builder()
                .id(live.getId())
                .title(live.getTitle())
                .sectionId(live.getSectionId())
                .userId(live.getUserId())
                .user(user)
                .startTime(live.getStartTime())
                .endTime(live.getEndTime())
                .createTime(live.getCreateTime())
                .updateTime(live.getUpdateTime())
                .build();
    }

    public static List<LiveDTO> fromEntities(List<Live> lives, Map<Long, UserDTO> users) {
        return lives.stream()
                .map(live -> LiveDTO.fromEntity(live, users.get(live.getUserId())))
                .collect(Collectors.toList());
    }
}
