package deny.football.data.infrastructure;

import deny.football.data.infrastructure.PlayerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<PlayerDocument, Long> {

    List<PlayerDocument> findAllByMaxMarketValueGreaterThanEqual(BigDecimal maxMarketValue);
}
