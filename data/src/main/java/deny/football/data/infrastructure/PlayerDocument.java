package deny.football.data.infrastructure;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.mapping.FieldType.DECIMAL128;

@Document("player")
public class PlayerDocument {
    @Id
    private final Long id;
    private String imageURL;
    private String name;
    private String position;
    private String dateOfBirth;
    private Integer age;
    private List<String> nationalities;
    @Field(targetType = DECIMAL128)
    private BigDecimal maxMarketValue;
    @Field(targetType = DECIMAL128)
    private BigDecimal currentMarketValue;
    private final List<Transfer> transfers;
    private final List<MarketValue> marketValues;

    public PlayerDocument(Long id, List<Transfer> transfers, List<MarketValue> marketValues) {
        this.id = id;
        this.transfers = transfers;
        this.marketValues = marketValues;
        this.maxMarketValue = marketValues.stream()
                .map(MarketValue::getValue)
                .filter(Objects::nonNull)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    public Long getId() {
        return id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getNationalities() {
        return nationalities;
    }

    public void setNationalities(List<String> nationalities) {
        this.nationalities = nationalities;
    }

    public BigDecimal getMaxMarketValue() {
        return maxMarketValue;
    }

    public void setMaxMarketValue(BigDecimal maxMarketValue) {
        this.maxMarketValue = maxMarketValue;
    }

    public BigDecimal getCurrentMarketValue() {
        return currentMarketValue;
    }

    public void setCurrentMarketValue(BigDecimal currentMarketValue) {
        this.currentMarketValue = currentMarketValue;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public List<MarketValue> getMarketValues() {
        return marketValues;
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
