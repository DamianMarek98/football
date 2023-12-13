package deny.football.data.logic;

import deny.football.data.infrastructure.ClubRepository;
import deny.football.data.transfermarkt.dto.ClubProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubsCreator {
    private final ClubRepository clubRepository;

    @Autowired
    public ClubsCreator(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public void createClubs(List<ClubProfile> clubProfiles) {
        var clubs = clubProfiles.stream()
                .map(ClubDocumentFactory::create)
                .toList();
        clubRepository.saveAll(clubs);
    }
}
