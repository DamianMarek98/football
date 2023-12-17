package deny.football.data.transfermarkt.web.dto.response;

import java.util.List;

public record PlayerSearchResult(Integer pageNumber,
                                 Integer lastPageNumber,
                                 List<PlayerResult> players) {
}
