package deny.football.data.journey.duel.events.handlers;

import deny.football.data.journey.duel.events.PlayerWonEvent;
import deny.football.data.journey.duel.web.GameNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PlayerWonEventHandler {
    private final GameNotifier gameNotifier;

    @Autowired
    public PlayerWonEventHandler(GameNotifier gameNotifier) {
        this.gameNotifier = gameNotifier;
    }

    @EventListener
    public void handlePlayerWonEvent(PlayerWonEvent event) {
        gameNotifier.sendGameFinished(event.playerName(), event.journeyPlayerName(), event.imageURL());
    }
}
