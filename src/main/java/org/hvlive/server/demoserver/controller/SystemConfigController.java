package org.hvlive.server.demoserver.controller;

import org.hvlive.server.demoserver.dto.SystemConfigDTO;
import org.hvlive.server.demoserver.service.SystemConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/systemConfig")
public class SystemConfigController {
    private SystemConfigService systemConfigService;

    public SystemConfigController(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
    }

    @GetMapping
    public SystemConfigDTO getSystemConfig() {
        return systemConfigService.getSystemConfig();
    }

    @PutMapping
    public SystemConfigDTO updateSystemConfig(@RequestBody SystemConfigDTO systemConfig) {
        return systemConfigService.updateSystemConfig(systemConfig);
    }
}
