package org.hvlive.server.demoserver.repository;

import org.hvlive.server.demoserver.entity.Section;
import org.hvlive.server.demoserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findAllByChannelId(Long channelId);

    List<Section> findAllByIdIn(Set<Long> ids);

    boolean existsByChannelIdAndName(Long channelId, String name);

    boolean existsByIdNotAndChannelIdAndName(Long id, Long channelId, String name);
}
