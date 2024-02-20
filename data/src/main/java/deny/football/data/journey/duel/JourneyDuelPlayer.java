package deny.football.data.journey.duel;

import java.util.UUID;

public class JourneyDuelPlayer {
    private final UUID id;
    private final String name;

    public JourneyDuelPlayer(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
