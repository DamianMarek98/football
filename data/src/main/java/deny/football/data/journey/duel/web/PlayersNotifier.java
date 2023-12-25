package deny.football.data.journey.duel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PlayersNotifier {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public PlayersNotifier(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Async
    public void sendPlayers(Collection<String> players) {
        simpMessagingTemplate.convertAndSend("/topic/players", players);
    }
}
