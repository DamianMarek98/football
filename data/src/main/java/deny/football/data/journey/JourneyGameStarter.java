package deny.football.data.journey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JourneyGameStarter {
    private final JourneyGameFactory journeyGameFactory;

    @Autowired
    public JourneyGameStarter(JourneyGameFactory journeyGameFactory) {
        this.journeyGameFactory = journeyGameFactory;
    }

    public JourneyGame startGame() {
        return journeyGameFactory.createGameJourney();
    }
}
