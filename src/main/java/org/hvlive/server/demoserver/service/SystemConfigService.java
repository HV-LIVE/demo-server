package org.hvlive.server.demoserver.service;

import org.hvlive.server.demoserver.dto.SystemConfigDTO;
import org.hvlive.server.demoserver.entity.SystemConfig;
import org.hvlive.server.demoserver.repository.SystemConfigRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SystemConfigService {
    private SystemConfigRepository systemConfigRepository;

    public SystemConfigService(SystemConfigRepository systemConfigRepository) {
        this.systemConfigRepository = systemConfigRepository;
    }

    public SystemConfigDTO getSystemConfig() {
        return systemConfigRepository.findById(1L)
                .map(SystemConfigDTO::fromEntity)
                .orElse(null);
    }

    @Transactional
    public SystemConfigDTO updateSystemConfig(SystemConfigDTO systemConfigDTO) {
        SystemConfig systemConfig = SystemConfig.builder()
                .id(1L)
                .pushIp(systemConfigDTO.getPushIp())
                .pushPort(systemConfigDTO.getPushPort())
                .pullIp(systemConfigDTO.getPullIp())
                .pullPort(systemConfigDTO.getPullPort())
                .build();
        systemConfigRepository.saveAndFlush(systemConfig);
        return SystemConfigDTO.fromEntity(systemConfig);
    }
}
