package org.hvlive.server.demoserver.controller;

import org.hvlive.server.demoserver.dto.LiveDTO;
import org.hvlive.server.demoserver.service.LiveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lives")
public class LivesController {
    private LiveService liveService;

    public LivesController(LiveService liveService) {
        this.liveService = liveService;
    }

    @GetMapping
    public List<LiveDTO> getLives(@RequestParam(required = false) Long userId) {
        return userId == null ? liveService.getAllLives() : liveService.getLives(userId);
    }

    @GetMapping("/available")
    public List<LiveDTO> getAvailableLives() {
        return liveService.getAvailableLives();
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
