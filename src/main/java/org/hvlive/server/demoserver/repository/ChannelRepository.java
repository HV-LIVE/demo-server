package org.hvlive.server.demoserver.repository;

import org.hvlive.server.demoserver.entity.Channel;
import org.hvlive.server.demoserver.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    List<Channel> findAllByIdIn(Set<Long> ids);

    boolean existsByName(String name);

    boolean existsByIdNotAndName(Long id, String name);
}
