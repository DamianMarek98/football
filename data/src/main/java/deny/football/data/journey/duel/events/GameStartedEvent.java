package deny.football.data.journey.duel.events;

import deny.football.data.commons.result.DomainEvent;

public record GameStartedEvent(Long playerId) implements DomainEvent {
}
