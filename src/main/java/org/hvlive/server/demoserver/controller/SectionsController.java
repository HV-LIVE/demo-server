package org.hvlive.server.demoserver.controller;

import org.hvlive.server.demoserver.dto.SectionDTO;
import org.hvlive.server.demoserver.service.SectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
public class SectionsController {
    private SectionService sectionService;

    public SectionsController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping
    public List<SectionDTO> getSections(@RequestParam Long channelId) {
        return sectionService.getSections(channelId);
    }

    @PostMapping
    public SectionDTO createSection(@RequestBody SectionDTO section) {
        return sectionService.createSection(section);
    }

    @PutMapping
    public SectionDTO updateSection(@RequestBody SectionDTO section) {
        return sectionService.updateSection(section);
    }

    @DeleteMapping("/{id}")
    public void deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
    }
}
