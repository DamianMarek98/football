package deny.football.data.journey;

import java.util.UUID;

public class JourneyGame {
    private final UUID uuid = UUID.randomUUID();
    private final Long playerJourneyId;
    private int guessesMade = 0;

    public JourneyGame(Long playerJourneyId) {
        this.playerJourneyId = playerJourneyId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Long getPlayerJourneyId() {
        return playerJourneyId;
    }

    public int getGuessesMade() {
        return guessesMade;
    }

    public void setGuessesMade(int guessesMade) {
        this.guessesMade = guessesMade;
    }
}
