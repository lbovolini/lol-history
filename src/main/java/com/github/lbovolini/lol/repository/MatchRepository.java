package com.github.lbovolini.lol.repository;

import net.rithms.riot.api.endpoints.match.dto.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends MongoRepository<Match, Long> {
}
