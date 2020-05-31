package org.hvlive.server.demoserver.controller;

import org.hvlive.server.demoserver.dto.LiveDTO;
import org.hvlive.server.demoserver.service.LiveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/live")
public class LivesController {
    private LiveService liveService;

    public LivesController(LiveService liveService) {
        this.liveService = liveService;
    }

    @GetMapping
    public List<LiveDTO> getLives(@RequestParam Long sectionId) {
        return liveService.getLives(sectionId);
    }

    @PostMapping
    public LiveDTO createLive(@RequestBody LiveDTO live) {
        return liveService.createLive(live);
    }

    @PutMapping
    public LiveDTO updateLive(@RequestBody LiveDTO live) {
        return liveService.updateLive(live);
    }

    @DeleteMapping("/{id}")
    public void deleteLive(@PathVariable Long id) {
        liveService.deleteLive(id);
    }
}
