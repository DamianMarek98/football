package deny.football.data.journey;

import deny.football.data.infrastructure.PlayerDocument;
import deny.football.data.infrastructure.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Component
public class JourneyGameFactory {
    public static final Random RANDOM = new Random();
    private final PlayerRepository playerRepository;

    @Autowired
    public JourneyGameFactory(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public JourneyGame createGameJourney() {
        List<PlayerDocument> players = playerRepository.findAllByMaxMarketValueGreaterThanEqual(new BigDecimal("15000.00"));
        var player = players.get(RANDOM.nextInt(players.size()));
        return new JourneyGame(player.getId());
    }
}
