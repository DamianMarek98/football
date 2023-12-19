package deny.football.data.journey;

import deny.football.data.infrastructure.journey.JourneyGameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JourneyGameStarter {
    private final JourneyGameFactory journeyGameFactory;
    private final JourneyGameRepo journeyGameRepo;

    @Autowired
    public JourneyGameStarter(JourneyGameFactory journeyGameFactory, JourneyGameRepo journeyGameRepo) {
        this.journeyGameFactory = journeyGameFactory;
        this.journeyGameRepo = journeyGameRepo;
    }

    public JourneyGame startGame() {
        JourneyGame gameJourney = journeyGameFactory.createGameJourney();
        journeyGameRepo.save(gameJourney);
        return gameJourney;
    }
}
