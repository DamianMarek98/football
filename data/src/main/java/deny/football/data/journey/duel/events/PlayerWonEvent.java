package deny.football.data.journey.duel.events;

import deny.football.data.commons.result.DomainEvent;

public record PlayerWonEvent(String playerName,
                             String journeyPlayerName,
                             String imageURL) implements DomainEvent {
}
