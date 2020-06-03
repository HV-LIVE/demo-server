package org.hvlive.server.demoserver.repository;

import org.hvlive.server.demoserver.entity.Live;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LiveRepository extends JpaRepository<Live, Long> {
    List<Live> findAllByUserId(Long userId);

    List<Live> findAllByStartTimeLessThanAndEndTimeGreaterThan(LocalDateTime startTime, LocalDateTime endTime);

    boolean existsByUserIdAndStartTimeBetween(Long userId, LocalDateTime startTime, LocalDateTime endTime);

    boolean existsByUserIdAndEndTimeBetween(Long userId, LocalDateTime startTime, LocalDateTime endTime);

    boolean existsByIdNotAndUserIdAndStartTimeBetween(Long id, Long userId, LocalDateTime startTime, LocalDateTime endTime);

    boolean existsByIdNotAndUserIdAndEndTimeBetween(Long id, Long userId, LocalDateTime startTime, LocalDateTime endTime);
}
