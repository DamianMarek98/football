package deny.football.data.journey.duel.events;

import deny.football.data.commons.result.DomainEvent;

import java.util.Collection;

public record PlayerJoinedEvent(Collection<String> playerNames) implements DomainEvent {
}
