package deny.football.data.journey.duel.events;

import java.util.Collection;

public record PlayerJoinedEvent(Collection<String> players) {
}
