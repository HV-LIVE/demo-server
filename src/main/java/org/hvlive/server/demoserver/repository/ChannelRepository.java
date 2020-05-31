package org.hvlive.server.demoserver.repository;

import org.hvlive.server.demoserver.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    boolean existsByName(String name);

    boolean existsByIdNotAndName(Long id, String name);
}
