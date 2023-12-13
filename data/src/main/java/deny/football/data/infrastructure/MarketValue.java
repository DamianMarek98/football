package deny.football.data.infrastructure;

import deny.football.data.logic.TransfermarktValueMapper;

import java.math.BigDecimal;

public class MarketValue {
    private final Integer age;
    private final String date;
    private final String valueString;
    private final BigDecimal value;
    private final Long clubId;

    public MarketValue(Integer age, String date, String valueString, Long clubId) {
        this.age = age;
        this.date = date;
        this.valueString = valueString;
        this.clubId = clubId;
        this.value = TransfermarktValueMapper.map(valueString);
    }

    public static MarketValue from(deny.football.data.transfermarkt.dto.MarketValue marketValueDto) {
        return new MarketValue(marketValueDto.age(), marketValueDto.date(), marketValueDto.value(), marketValueDto.clubID());
    }

    public Integer getAge() {
        return age;
    }

    public String getDate() {
        return date;
    }

    public String getValueString() {
        return valueString;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Long getClubId() {
        return clubId;
    }
}
