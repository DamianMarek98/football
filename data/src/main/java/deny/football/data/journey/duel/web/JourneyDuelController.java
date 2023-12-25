package deny.football.data.journey.duel.web;

import deny.football.data.journey.duel.JourneyGameOrchestrator;
import deny.football.data.journey.duel.web.dto.JourneyGamePlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/journey-games")
public class JourneyDuelController {
    private final JourneyGameOrchestrator journeyGameOrchestrator;

    @Autowired
    public JourneyDuelController(JourneyGameOrchestrator journeyGameOrchestrator) {
        this.journeyGameOrchestrator = journeyGameOrchestrator;
    }

    @GetMapping("/exists")
    public Boolean getCurrentGame() {
        return journeyGameOrchestrator.gameExists();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewGame() {
        journeyGameOrchestrator.createNewGame();
    }

    @PostMapping("/join/{name}")
    public UUID joinGame(@PathVariable String name) {
        return journeyGameOrchestrator.join(name);
    }

    @GetMapping("/players")
    public List<JourneyGamePlayerDto> getPlayers() {
        return journeyGameOrchestrator.getPlayerNames().stream()
                .map(JourneyGamePlayerDto::new)
                .toList();
    }
}
