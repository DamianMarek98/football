package deny.football.data.transfermarkt;

import deny.football.data.transfermarkt.dto.Competition;
import deny.football.data.transfermarkt.dto.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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

    public List<Player> getPlayers(Long clubId) {
        return Objects.requireNonNull(restTemplate.getForObject(BASE_URL + "/clubs/" + clubId + "/players", ClubPlayersResponse.class))
                .players();
    }

    private record ClubPlayersResponse(String id, List<Player> players) {}
}
