package deny.football.data.journey.duel;

import deny.football.data.infrastructure.PlayerDocument;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JourneyDuel {
    private final Map<UUID, String> playerIdNameMap;
    private final PlayerDocument player;

    public JourneyDuel(PlayerDocument playerDocument) {
        this.player = playerDocument;
        this.playerIdNameMap = new HashMap<>();
    }

    public JourneyDuel(Map<UUID, String> playerIdNameMap, PlayerDocument player) {
        this.playerIdNameMap = playerIdNameMap;
        this.player = player;
    }

    public UUID addPlayer(String playerName) {
        UUID uuid = UUID.randomUUID();
        playerIdNameMap.put(uuid, playerName);
        return uuid;
    }

    public boolean makeGuess(Long playerId) {
        return player.getId().equals(playerId);
    }

    public Map<UUID, String> getPlayerIdNameMap() {
        return new HashMap<>(playerIdNameMap);
    }

    public Long getPlayerId() {
        return player.getId();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public String getPlayerImageURL() {
        return player.getImageURL();
    }
}
