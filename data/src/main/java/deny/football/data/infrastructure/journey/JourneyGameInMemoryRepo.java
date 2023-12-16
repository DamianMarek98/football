package deny.football.data.infrastructure.journey;

import deny.football.data.journey.JourneyGame;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
class JourneyGameInMemoryRepo implements JourneyGameRepo {
    private final Map<UUID, JourneyGame> uuidJourneyGameMap = new HashMap<>();

    @Override
    public JourneyGame save(JourneyGame journeyGame) {
        return uuidJourneyGameMap.put(journeyGame.getUuid(), journeyGame);
    }

    @Override
    public JourneyGame getById(UUID uuid) {
        return uuidJourneyGameMap.get(uuid);
    }
}
