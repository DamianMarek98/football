package deny.football.data.journey.duel;

import deny.football.data.journey.duel.events.GameStartedEvent;
import deny.football.data.journey.duel.events.PlayerJoinedEvent;
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
        return currentDuel.getPlayerIdNameMap()
                .values()
                .stream()
                .toList();
    }

    public UUID join(String name) {
        UUID uuid = currentDuel.addPlayer(name);
        applicationEventPublisher.publishEvent(new PlayerJoinedEvent(currentDuel.getPlayerIdNameMap().values()));
        return uuid;
    }

    public void startGame() {
        if (currentDuel == null) {
            throw new RuntimeException("Game not created!");
        }
        applicationEventPublisher.publishEvent(new GameStartedEvent(currentDuel.getPlayerId()));
    }

    public boolean makeGuess(Long playerId) {
        var result = currentDuel.makeGuess(playerId);
        //todo notify if wygranko
        return result;
    }

    public boolean gameExists() {
        return currentDuel != null;
    }
}
