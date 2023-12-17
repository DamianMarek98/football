package deny.football.data.journey.handlers;

import deny.football.data.journey.events.JourneyGameGuessMadeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class GuessesCounterTracker {
    @EventListener
    public void handle(JourneyGameGuessMadeEvent journeyGameGuessMadeEvent) {
        journeyGameGuessMadeEvent.journeyGame().handleGuess(journeyGameGuessMadeEvent.guessedPlayerId());
    }
}
