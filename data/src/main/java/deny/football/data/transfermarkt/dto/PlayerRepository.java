package deny.football.data.transfermarkt.dto;

import deny.football.data.infrastructure.PlayerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<PlayerDocument, Long> {
}
