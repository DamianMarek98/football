package deny.football.data.infrastructure;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("club")
public class ClubDocument {
    @Id
    private final Long id;
    private final String name;
    private final String image;

    public ClubDocument(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
