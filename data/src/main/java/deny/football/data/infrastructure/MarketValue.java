package deny.football.data.infrastructure;

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
        this.value = mapToBigDecimalValue(valueString);
    }

    public BigDecimal mapToBigDecimalValue(String valueString) {
        if (valueString == null) {
            return null;
        }
        var valueStringWithoutEuroSign = valueString.replace("€", "");
        if (valueStringWithoutEuroSign.contains("k")) {
            return new BigDecimal(valueStringWithoutEuroSign.replace("k", ""));
        } else if (valueStringWithoutEuroSign.contains("m")) {
            return new BigDecimal(valueStringWithoutEuroSign.replace("m", "")).multiply(BigDecimal.valueOf(1000));
        } else {
            throw new RuntimeException(valueStringWithoutEuroSign + " couldn't be map to BigDecimal");
        }
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
