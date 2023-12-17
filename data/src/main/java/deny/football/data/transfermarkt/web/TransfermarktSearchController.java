package deny.football.data.transfermarkt.web;

import deny.football.data.transfermarkt.TransfermarktFacade;
import deny.football.data.transfermarkt.dto.SearchResponse;
import deny.football.data.transfermarkt.web.dto.request.PlayerSearchRequest;
import deny.football.data.transfermarkt.web.dto.response.PlayerResult;
import deny.football.data.transfermarkt.web.dto.response.PlayerSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfermarkt-search")
public class TransfermarktSearchController {
    private final TransfermarktFacade transfermarktFacade;

    @Autowired
    public TransfermarktSearchController(TransfermarktFacade transfermarktFacade) {
        this.transfermarktFacade = transfermarktFacade;
    }

    @PostMapping
    public PlayerSearchResult searchPlayers(@RequestBody PlayerSearchRequest request) {
        SearchResponse transfermarktSearchResult = transfermarktFacade.search(request.searchText(), request.page());
        return new PlayerSearchResult(transfermarktSearchResult.pageNumber(), transfermarktSearchResult.lastPageNumber(),
                transfermarktSearchResult.results().stream()
                        .map(result -> new PlayerResult(result.id(), result.name(), result.position(), result.age()))
                        .toList());
    }
}
