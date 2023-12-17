package deny.football.data.journey.web.converter;

import deny.football.data.journey.JourneyGame;
import deny.football.data.journey.web.dto.JourneyGameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JourneyGameDtoConverter implements Converter<JourneyGame, JourneyGameDto> {
    private final TransferDtoConverter transferDtoConverter;

    @Autowired
    public JourneyGameDtoConverter(TransferDtoConverter transferDtoConverter) {
        this.transferDtoConverter = transferDtoConverter;
    }

    @Override
    public JourneyGameDto convert(JourneyGame journeyGame) {
        return new JourneyGameDto(journeyGame.getUuid(), transferDtoConverter.convert(journeyGame.getPlayerJourneyId()));
    }
}
