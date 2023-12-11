package deny.football.data.infrastructure;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("testItem")
public class PlayerDocument {
    @Id
    private Long id;
    private String name;
    private String position;
    private String dateOfBirth;
    private Integer age;
    private List<String> nationality;

    public PlayerDocument(Long id, String name, String position, String dateOfBirth, Integer age) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PlayerDocument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", age=" + age +
                '}';
    }
}
