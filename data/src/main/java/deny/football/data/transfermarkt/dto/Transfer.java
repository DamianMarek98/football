package deny.football.data.transfermarkt.dto;

public record Transfer(Long id,
                       TransferClub from,
                       TransferClub to,
                       String date,
                       Boolean upcoming,
                       String season) {
}
