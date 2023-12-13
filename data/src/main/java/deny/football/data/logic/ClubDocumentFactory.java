package deny.football.data.logic;

import deny.football.data.infrastructure.ClubDocument;
import deny.football.data.transfermarkt.dto.ClubProfile;

class ClubDocumentFactory {

    private ClubDocumentFactory() {
    }

    public static ClubDocument create(ClubProfile clubProfile) {
        return new ClubDocument(clubProfile.id(), clubProfile.name(), clubProfile.image());
    }
}
