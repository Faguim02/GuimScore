package com.app.guimscore.repository;

import com.app.guimscore.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends MongoRepository<Player, UUID> {
    Optional<Player> findByName(String name);
    List<Player> findByGameServerId(UUID gameServerId);
    boolean existsByName(String name);
}
