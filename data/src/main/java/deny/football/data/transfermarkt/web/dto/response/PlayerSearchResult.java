package deny.football.data.transfermarkt.web.dto.response;

import java.util.List;

public record PlayerSearchResult(Integer pageNumber,
                                 Integer lastPageNumber,
                                 List<PlayerResult> players) {
    public static PlayerSearchResult empty() {
        return new PlayerSearchResult(0, 0, List.of());
    }
}
