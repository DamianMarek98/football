package deny.football.data.journey.duel.events.handlers;

import deny.football.data.journey.duel.events.GameStartedEvent;
import deny.football.data.journey.duel.web.GameNotifier;
import deny.football.data.journey.web.converter.TransferDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GameStartedEventHandler {
    private final GameNotifier gameNotifier;
    private final TransferDtoConverter transferDtoConverter;

    @Autowired
    public GameStartedEventHandler(GameNotifier gameNotifier, TransferDtoConverter transferDtoConverter) {
        this.gameNotifier = gameNotifier;
        this.transferDtoConverter = transferDtoConverter;
    }

    @EventListener
    public void handleContextStart(GameStartedEvent event) {
        var transfers = transferDtoConverter.convert(event.playerId());
        gameNotifier.sendGameStarted(transfers);
    }
}
