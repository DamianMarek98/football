package deny.football.data.journey.duel;

import deny.football.data.infrastructure.PlayerDocument;
import deny.football.data.infrastructure.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Component
public class JourneyDuelFactory {
    public static final Random RANDOM = new Random();
    private final PlayerRepository playerRepository;

    @Autowired
    public JourneyDuelFactory(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public JourneyDuel createGameDuel() {
        List<PlayerDocument> players = playerRepository.findAllByMaxMarketValueGreaterThanEqual(new BigDecimal("15000.00"));
        var player = players.get(RANDOM.nextInt(players.size()));
        return new JourneyDuel(player);
    }

    public JourneyDuel createGameDuel(JourneyDuel journeyDuel) {
        List<PlayerDocument> players = playerRepository.findAllByMaxMarketValueGreaterThanEqual(new BigDecimal("15000.00"));
        var player = players.get(RANDOM.nextInt(players.size()));
        return new JourneyDuel(journeyDuel.getPlayerIdNameMap(), player);
    }
}
