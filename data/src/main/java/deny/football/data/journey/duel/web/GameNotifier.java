package deny.football.data.journey.duel.web;

import deny.football.data.journey.web.dto.TransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameNotifier {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public GameNotifier(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Async
    public void sendGameStarted(List<TransferDto> transfers) {
        simpMessagingTemplate.convertAndSend("/topic/game-started", transfers);
    }
}
