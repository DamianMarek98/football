package deny.football.data;

import deny.football.data.transfermarkt.TransfermarktFacade;
import deny.football.data.transfermarkt.dto.Competition;
import deny.football.data.transfermarkt.dto.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final TransfermarktFacade transfermarktFacade;

    @Autowired
    public TestController(TransfermarktFacade transfermarktFacade) {
        this.transfermarktFacade = transfermarktFacade;
    }

    @GetMapping
    public Competition test() {
        return transfermarktFacade.getCompetitionClubs("GB1");
    }

    @GetMapping("/players")
    public List<Player> test2() {
        return transfermarktFacade.getPlayers(31L);
    }
}
