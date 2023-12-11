package deny.football.data.transfermarkt.dto;

public record MarketValue(Integer age,
                          String date,
                          String clubName,
                          String value,
                          Long clubID) {
}
