package org.hvlive.server.demoserver.controller;

import org.hvlive.server.demoserver.dto.ChannelDTO;
import org.hvlive.server.demoserver.service.ChannelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/channel")
public class ChannelsController {
    private ChannelService channelService;

    public ChannelsController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping
    public List<ChannelDTO> getAllChannels() {
        return channelService.getAllChannels();
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
