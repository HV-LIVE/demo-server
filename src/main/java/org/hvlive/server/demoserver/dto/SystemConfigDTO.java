package org.hvlive.server.demoserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hvlive.server.demoserver.entity.SystemConfig;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemConfigDTO {
    private String pushIp;
    private Integer pushPort;
    private String pullIp;
    private Integer pullPort;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static SystemConfigDTO fromEntity(SystemConfig systemConfig) {
        return SystemConfigDTO.builder()
                .pushIp(systemConfig.getPushIp())
                .pushPort(systemConfig.getPushPort())
                .pullIp(systemConfig.getPullIp())
                .pullPort(systemConfig.getPullPort())
                .createTime(systemConfig.getCreateTime())
                .updateTime(systemConfig.getUpdateTime())
                .build();
    }
}
