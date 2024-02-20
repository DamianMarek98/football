package deny.football.data.journey.duel;

import deny.football.data.commons.result.Result;
import deny.football.data.infrastructure.PlayerDocument;
import deny.football.data.journey.duel.events.GameStartedEvent;
import deny.football.data.journey.duel.events.PlayerJoinedEvent;
import deny.football.data.journey.duel.events.PlayerWonEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JourneyDuel {
    private final Map<UUID, JourneyDuelPlayer> playerIdPlayerMap;
    private final PlayerDocument player;

    public JourneyDuel(PlayerDocument playerDocument) {
        this.player = playerDocument;
        this.playerIdPlayerMap = new HashMap<>();
    }

    public JourneyDuel(Map<UUID, JourneyDuelPlayer> playerIdPlayerMap, PlayerDocument player) {
        this.playerIdPlayerMap = playerIdPlayerMap;
        this.player = player;
    }

    public Result startGame() {
        return Result.success(new GameStartedEvent(player.getId()));
    }

    public Result addPlayer(JourneyDuelPlayer player) {
        playerIdPlayerMap.put(player.getId(), player);
        return Result.success(new PlayerJoinedEvent(getPlayerNames()));
    }

    public Result makeGuess(UUID playerGuessingId, Long playerId) {
        if (isWinningGuess(playerId)) {
            PlayerWonEvent playerWonEvent = new PlayerWonEvent(playerIdPlayerMap.get(playerGuessingId).getName(),
                    player.getName(),
                    player.getImageURL());
            return Result.success(playerWonEvent);
        }
        return Result.failure("Wrong player guessed");
    }

    private boolean isWinningGuess(Long playerId) {
        return player.getId().equals(playerId);
    }

    public Map<UUID, JourneyDuelPlayer> getPlayerIdPlayerMap() {
        return new HashMap<>(playerIdPlayerMap);
    }

    List<String> getPlayerNames() {
        return playerIdPlayerMap.values().stream()
                .map(JourneyDuelPlayer::getName)
                .toList();
    }
}
