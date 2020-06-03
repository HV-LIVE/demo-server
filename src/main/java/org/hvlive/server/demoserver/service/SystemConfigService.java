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
        SystemConfig systemConfig = systemConfigRepository.findById(1L)
                .orElseGet(() -> SystemConfig.builder().id(1L).build());
        systemConfig.setPushIp(systemConfigDTO.getPushIp());
        systemConfig.setPushPort(systemConfigDTO.getPushPort());
        systemConfig.setPullIp(systemConfigDTO.getPullIp());
        systemConfig.setPullPort(systemConfigDTO.getPullPort());
        systemConfigRepository.saveAndFlush(systemConfig);
        return SystemConfigDTO.fromEntity(systemConfig);
    }
}
