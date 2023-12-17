package deny.football.data.journey.events;

import deny.football.data.journey.JourneyGame;

public record JourneyGameGuessMadeEvent(JourneyGame journeyGame, long guessedPlayerId) {
}
