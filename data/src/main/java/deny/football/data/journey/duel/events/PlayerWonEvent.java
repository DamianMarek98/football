package deny.football.data.journey.duel.events;

public record PlayerWonEvent(String playerName,
                             String journeyPlayerName,
                             String imageURL) {
}
