package deny.football.data.transfermarkt.dto;

import java.util.List;

public record Player(Long id,
                     String name,
                     String position,
                     String dateOfBirth,
                     Integer age,
                     List<String> nationality,
                     String height,
                     String foot,
                     String joinedOn,
                     String signedFrom,
                     String contract,
                     String marketValue) {

}
