package deny.football.data.journey.duel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JourneyGameOrchestrator {
    private JourneyDuel currentDuel;
    private final JourneyDuelFactory journeyDuelFactory;

    @Autowired
    public JourneyGameOrchestrator(JourneyDuelFactory journeyDuelFactory) {
        this.journeyDuelFactory = journeyDuelFactory;
    }

    public void createNewGame() {
        if (currentDuel == null) {
            currentDuel = journeyDuelFactory.createGameDuel();
        } else {
            currentDuel = journeyDuelFactory.createGameDuel(currentDuel);
        }
        //todo notify
    }

    public List<String> getPlayerNames() {
        return currentDuel.getPlayerIdNameMap()
                .values()
                .stream()
                .toList();
    }

    public UUID join(String name) {
        return currentDuel.addPlayer(name);
    }

    public boolean makeGuess(Long playerId) {
        var result = currentDuel.makeGuess(playerId);
        //todo notify if wygranko
        return result;
    }
}
