package deny.football.data.journey.duel;

import deny.football.data.journey.duel.exceptions.GameNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JourneyGameOrchestrator {
    private JourneyDuel currentDuel;
    private final JourneyDuelFactory journeyDuelFactory;
    private final ApplicationEventPublisher applicationEventPublisher;


    @Autowired
    public JourneyGameOrchestrator(JourneyDuelFactory journeyDuelFactory, ApplicationEventPublisher applicationEventPublisher) {
        this.journeyDuelFactory = journeyDuelFactory;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void createNewGame() {
        if (currentDuel == null) {
            currentDuel = journeyDuelFactory.createGameDuel();
        } else {
            currentDuel = journeyDuelFactory.createGameDuel(currentDuel);
        }
    }

    public List<String> getPlayerNames() {
        return currentDuel.getPlayerNames();
    }

    public UUID join(String name) {
        JourneyDuelPlayer journeyDuelPlayer = new JourneyDuelPlayer(name);
        var result = currentDuel.addPlayer(journeyDuelPlayer);
        result.getEvents().forEach(applicationEventPublisher::publishEvent);
        return journeyDuelPlayer.getId();
    }

    public void startGame() {
        if (currentDuel == null) {
            throw new GameNotCreatedException();
        }
        var result = currentDuel.startGame();
        result.getEvents().forEach(applicationEventPublisher::publishEvent);
    }

    public boolean makeGuess(UUID id, Long playerId) {
        var result = currentDuel.makeGuess(id, playerId);
        result.getEvents().forEach(applicationEventPublisher::publishEvent);
        return result.isSuccessful();
    }

    public boolean gameExists() {
        return currentDuel != null && !currentDuel.getPlayerIdPlayerMap().isEmpty();
    }
}
