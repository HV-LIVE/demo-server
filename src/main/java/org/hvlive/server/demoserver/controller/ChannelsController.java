package org.hvlive.server.demoserver.controller;

import org.hvlive.server.demoserver.dto.ChannelDTO;
import org.hvlive.server.demoserver.dto.LiveDTO;
import org.hvlive.server.demoserver.service.ChannelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelsController {
    private ChannelService channelService;

    public ChannelsController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public List<ChannelDTO> getAllChannels(@RequestParam(required = false, defaultValue = "false") boolean excludeSections) {
        return excludeSections ? channelService.getAllChannels() : channelService.getAllChannelsAndSections();
    }

    @GetMapping("/available")
    public List<ChannelDTO> getAvailableChannels() {
        return channelService.getAvailableChannels();
    }

    @PostMapping
    public ChannelDTO createChannel(@RequestBody ChannelDTO channel) {
        return channelService.createChannel(channel);
    }

    @PutMapping
    public ChannelDTO updateChannel(@RequestBody ChannelDTO channel) {
        return channelService.updateChannel(channel);
    }

    @DeleteMapping("/{id}")
    public void deleteChannel(@PathVariable Long id) {
        channelService.deleteChannel(id);
    }
}
