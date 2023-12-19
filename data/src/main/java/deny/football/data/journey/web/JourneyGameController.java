package deny.football.data.journey.web;

import deny.football.data.journey.JourneyGameGuessService;
import deny.football.data.journey.JourneyGameStarter;
import deny.football.data.journey.web.converter.JourneyGameDtoConverter;
import deny.football.data.journey.web.dto.JourneyGameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/journey-games")
public class JourneyGameController {
    private final JourneyGameStarter journeyGameStarter;
    private final JourneyGameDtoConverter journeyGameDtoConverter;
    private final JourneyGameGuessService journeyGameGuessService;

    @Autowired
    public JourneyGameController(JourneyGameStarter journeyGameStarter, JourneyGameDtoConverter journeyGameDtoConverter, JourneyGameGuessService journeyGameGuessService) {
        this.journeyGameStarter = journeyGameStarter;
        this.journeyGameDtoConverter = journeyGameDtoConverter;
        this.journeyGameGuessService = journeyGameGuessService;
    }

    @PostMapping("/start")
    public JourneyGameDto startJourneyGame() {
        return journeyGameDtoConverter.convert(journeyGameStarter.startGame());
    }

    @PostMapping("/{gameId}/make-guess/{playerId}")
    public Boolean makeGuess(@PathVariable UUID gameId, @PathVariable Long playerId) {
        return journeyGameGuessService.guess(gameId,playerId);
    }
}
