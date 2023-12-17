package deny.football.data.journey.web.dto;

import java.util.List;
import java.util.UUID;

public record JourneyGameDto(UUID id,
                             List<TransferDto> transfers) {
}
