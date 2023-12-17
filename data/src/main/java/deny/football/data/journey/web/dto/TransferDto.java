package deny.football.data.journey.web.dto;

public record TransferDto(Long clubFromId,
                          String clubFromName,
                          String clubFromImage,
                          Long clubToId,
                          String clubToName,
                          String clubToImage,
                          String date,
                          Boolean upcoming,
                          String season,
                          String fee) {
}
