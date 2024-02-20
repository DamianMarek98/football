package deny.football.data.journey.duel.events.handlers;

import deny.football.data.journey.duel.events.PlayerJoinedEvent;
import deny.football.data.journey.duel.web.PlayersNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PlayerJoinedEventHandler {
    private final PlayersNotifier playersNotifier;

    @Autowired
    public PlayerJoinedEventHandler(PlayersNotifier playersNotifier) {
        this.playersNotifier = playersNotifier;
    }

    @EventListener
    public void handleContextStart(PlayerJoinedEvent event) {
        playersNotifier.sendPlayers(event.playerNames());
    }
}
