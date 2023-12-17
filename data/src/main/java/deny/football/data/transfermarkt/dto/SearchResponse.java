package deny.football.data.transfermarkt.dto;

import java.util.List;

public record SearchResponse(Integer pageNumber,
                             Integer lastPageNumber,
                             List<SearchResult> results) {
}
