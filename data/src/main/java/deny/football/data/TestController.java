package deny.football.data;

import deny.football.data.infrastructure.PlayerDocument;
import deny.football.data.transfermarkt.TransfermarktFacade;
import deny.football.data.transfermarkt.dto.Competition;
import deny.football.data.transfermarkt.dto.Player;
import deny.football.data.transfermarkt.dto.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final TransfermarktFacade transfermarktFacade;
    private final PlayerRepository playerRepository;

    @Autowired
    public TestController(TransfermarktFacade transfermarktFacade, PlayerRepository playerRepository) {
        this.transfermarktFacade = transfermarktFacade;
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public Competition test() {
        return transfermarktFacade.getCompetitionClubs("GB1");
    }

    @GetMapping("/players")
    public List<Player> test2() {
        return transfermarktFacade.getPlayers(31L);
    }

    @GetMapping("/mongo")
    public String test3() {
        playerRepository.save(new PlayerDocument(2L, "aa", "test", "aaa", 23));
        return playerRepository.findById(1L).orElseThrow().toString();
    }
}
