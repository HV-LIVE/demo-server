package org.hvlive.server.demoserver.repository;

import org.hvlive.server.demoserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByIdIn(Set<Long> ids);

    Optional<User> findByAccountAndPassword(String account, String password);

    boolean existsByAccount(String account);

    boolean existsByIdNotAndAccount(Long id, String account);
}
