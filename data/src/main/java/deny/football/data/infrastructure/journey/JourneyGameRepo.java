package deny.football.data.infrastructure.journey;

import deny.football.data.journey.JourneyGame;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface JourneyGameRepo {
    JourneyGame save(JourneyGame journeyGame);
    JourneyGame getById(UUID uuid);
}
