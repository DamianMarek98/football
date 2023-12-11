package deny.football.data.transfermarkt.dto;

import java.util.List;

public record Competition(String id,
                          String name,
                          String seasonID,
                          List<CompetitionClub> clubs) {
}
