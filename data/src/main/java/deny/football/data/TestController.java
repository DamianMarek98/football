package deny.football.data;

import deny.football.data.infrastructure.PlayerDocument;
import deny.football.data.infrastructure.PlayerRepository;
import deny.football.data.transfermarkt.TransfermarktFacade;
import deny.football.data.transfermarkt.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping
public class TestController {
    private final TransfermarktFacade transfermarktFacade;
    private final PlayerRepository playerRepository;

    @Autowired
    public TestController(TransfermarktFacade transfermarktFacade, PlayerRepository playerRepository) {
        this.transfermarktFacade = transfermarktFacade;
        this.playerRepository = playerRepository;
    }

    @GetMapping("/clubs/{leagueId}")
    public Competition getClubs(@PathVariable String leagueId) {
        return transfermarktFacade.getCompetitionClubs(leagueId);
    }

    @GetMapping("/clubs/{clubId}/profile")
    public ClubProfile getClubProfile(@PathVariable long clubId) {
        return transfermarktFacade.getClubProfile(clubId);
    }

    @GetMapping("/clubs/{clubId}/players")
    public List<Player> getClubPlayers(@PathVariable long clubId) {
        return transfermarktFacade.getPlayers(clubId);
    }

    @GetMapping("/players/{playerId}/profile")
    public PlayerProfile getPlayerProfile(@PathVariable long playerId) {
        return transfermarktFacade.getPlayerProfile(playerId);
    }

    @GetMapping("/players/{playerId}/transfers")
    public List<Transfer> getPlayerTransfers(@PathVariable long playerId) {
        return transfermarktFacade.getMarketTransfers(playerId);
    }

    @GetMapping("/players/{playerId}/market-values")
    public List<MarketValue> getPlayerMarketValues(@PathVariable long playerId) {
        return transfermarktFacade.getMarketValueHistory(playerId);
    }

    @GetMapping("/player/random/transfers")
    public List<deny.football.data.infrastructure.Transfer> getPlayers() {
        Random rand = new Random();
        List<PlayerDocument> players = playerRepository.findAllByMaxMarketValueGreaterThanEqual(new BigDecimal("10000.00"));
        var player = players.get(rand.nextInt(players.size()));
        return player.getTransfers();
    }
}
