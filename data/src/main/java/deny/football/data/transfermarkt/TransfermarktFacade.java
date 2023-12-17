package deny.football.data.transfermarkt;

import deny.football.data.transfermarkt.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class TransfermarktFacade {
    private static final String BASE_URL = "https://transfermarkt-api.vercel.app";
    private final RestTemplate restTemplate;

    @Autowired
    public TransfermarktFacade(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Competition getCompetitionClubs(String id) {
        return restTemplate.getForObject(BASE_URL + "/competitions/" + id + "/clubs", Competition.class);
    }

    public ClubProfile getClubProfile(Long clubId) {
        return restTemplate.getForObject(BASE_URL + "/clubs/" + clubId + "/profile", ClubProfile.class);
    }

    public PlayerProfile getPlayerProfile(Long playerId) {
        return restTemplate.getForObject(BASE_URL + "/players/" + playerId + "/profile", PlayerProfile.class);
    }

    public List<Player> getPlayers(Long clubId) {
        return Objects.requireNonNull(restTemplate.getForObject(BASE_URL + "/clubs/" + clubId + "/players", ClubPlayersResponse.class))
                .players();
    }

    public SearchResponse search(String searchText, int pageNumber) {
        return Objects.requireNonNull(restTemplate.getForObject(BASE_URL + "/search/" + searchText, SearchResponse.class, Map.of("page_number", pageNumber)));
    }

    private record ClubPlayersResponse(String id, List<Player> players) {}
    
    public List<MarketValue> getMarketValueHistory(Long playerId) {
        return Objects.requireNonNull(restTemplate.getForObject(BASE_URL + "/players/" + playerId + "/market_value", MarketValueResponse.class))
                .marketValueHistory();
    }

    private record MarketValueResponse(String id, List<MarketValue> marketValueHistory) {}

    public List<Transfer> getMarketTransfers(Long playerId) {
        return Objects.requireNonNull(restTemplate.getForObject(BASE_URL + "/players/" + playerId + "/transfers", TransfersResponse.class))
                .transfers();
    }

    private record TransfersResponse(String id, List<Transfer> transfers) {}
}
