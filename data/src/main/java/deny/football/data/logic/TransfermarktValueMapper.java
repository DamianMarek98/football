package deny.football.data.logic;

import java.math.BigDecimal;

public class TransfermarktValueMapper {
    private TransfermarktValueMapper() {
    }

    public static BigDecimal map(String valueString) {
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
}
