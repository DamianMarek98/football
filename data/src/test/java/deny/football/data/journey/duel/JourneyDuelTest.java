package deny.football.data.journey.duel;

import deny.football.data.infrastructure.PlayerDocument;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JourneyDuelTest {

    @Test
    void shouldStartGame() {
        var journeyDuel = new JourneyDuel(new PlayerDocument(1L, List.of(), List.of()));
        var result = journeyDuel.startGame();

        assertTrue(result.isSuccessful());
    }

    @Test
    void shouldAddPlayer() {
        var journeyDuel = new JourneyDuel(new PlayerDocument(1L, List.of(), List.of()));
        var result = journeyDuel.addPlayer(new JourneyDuelPlayer("test"));

        assertTrue(result.isSuccessful());
        assertThat(journeyDuel.getPlayerIdPlayerMap()).hasSize(1);
    }

    @Test
    void shouldReturnFailureWhenWrongPlayerGuess() {
        //given
        PlayerDocument playerToGuess = new PlayerDocument(1L, List.of(), List.of());
        var journeyDuel = new JourneyDuel(playerToGuess);
        JourneyDuelPlayer player = new JourneyDuelPlayer("test");
        journeyDuel.addPlayer(player);

        //when
        var result = journeyDuel.makeGuess(player.getId(), 404L);

        //then
        assertTrue(result.isFailure());
    }

    @Test
    void shouldReturnSuccessWhenCorrectGuess() {
        //given
        PlayerDocument playerToGuess = new PlayerDocument(1L, List.of(), List.of());
        var journeyDuel = new JourneyDuel(playerToGuess);
        JourneyDuelPlayer player = new JourneyDuelPlayer("test");
        journeyDuel.addPlayer(player);

        //when
        var result = journeyDuel.makeGuess(player.getId(), playerToGuess.getId());

        //then
        assertTrue(result.isSuccessful());
    }
}
