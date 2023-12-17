package deny.football.data.journey.web;

import deny.football.data.journey.JourneyGameStarter;
import deny.football.data.journey.web.converter.JourneyGameDtoConverter;
import deny.football.data.journey.web.dto.JourneyGameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journey-games")
public class JourneyGameController {
    private final JourneyGameStarter journeyGameStarter;
    private final JourneyGameDtoConverter journeyGameDtoConverter;

    @Autowired
    public JourneyGameController(JourneyGameStarter journeyGameStarter, JourneyGameDtoConverter journeyGameDtoConverter) {
        this.journeyGameStarter = journeyGameStarter;
        this.journeyGameDtoConverter = journeyGameDtoConverter;
    }

    @PostMapping("/start")
    public JourneyGameDto startJourneyGame() {
        return journeyGameDtoConverter.convert(journeyGameStarter.startGame());
    }
}
