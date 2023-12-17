package deny.football.data.journey;

import deny.football.data.infrastructure.journey.JourneyGameRepo;
import deny.football.data.journey.events.JourneyGameGuessMadeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class JourneyGameGuessService {
    private final JourneyGameRepo journeyGameRepo;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public JourneyGameGuessService(JourneyGameRepo journeyGameRepo, ApplicationEventPublisher eventPublisher) {
        this.journeyGameRepo = journeyGameRepo;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public boolean guess(UUID gameId, long guessedPlayerId) {
        var journeyGame = journeyGameRepo.getById(gameId);
        eventPublisher.publishEvent(new JourneyGameGuessMadeEvent(journeyGame, guessedPlayerId));
        journeyGameRepo.save(journeyGame);
        return journeyGame.isGameFinished();
    }
}
