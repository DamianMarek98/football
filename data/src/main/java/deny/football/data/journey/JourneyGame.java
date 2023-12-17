package deny.football.data.journey;

import java.util.UUID;

public class JourneyGame {
    private final UUID uuid = UUID.randomUUID();
    private final Long playerJourneyId;
    private int guessesMade = 0;
    private boolean gameFinished;

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

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void handleGuess(long guessedPlayerId) {
        guessesMade++;
        gameFinished = playerJourneyId.equals(guessedPlayerId);
    }
}
