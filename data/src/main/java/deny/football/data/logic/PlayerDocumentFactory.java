package deny.football.data.logic;

import deny.football.data.infrastructure.PlayerDocument;
import deny.football.data.transfermarkt.dto.MarketValue;
import deny.football.data.transfermarkt.dto.Player;
import deny.football.data.transfermarkt.dto.Transfer;

import java.util.List;

public class PlayerDocumentFactory {
    private PlayerDocumentFactory() {
    }

    public static PlayerDocument create(Player player, String imageUrl, List<Transfer> transfers, List<MarketValue> marketValues) {
        List<deny.football.data.infrastructure.Transfer> transferList = transfers == null ? List.of() : transfers.stream()
                .map(deny.football.data.infrastructure.Transfer::from)
                .toList();
        List<deny.football.data.infrastructure.MarketValue> marketValueList = marketValues == null ? List.of() : marketValues.stream()
                .map(deny.football.data.infrastructure.MarketValue::from)
                .toList();
        var playerDocument = new PlayerDocument(player.id(), transferList, marketValueList);
        playerDocument.setImageURL(imageUrl);
        playerDocument.setName(player.name());
        playerDocument.setPosition(player.position());
        playerDocument.setDateOfBirth(player.dateOfBirth());
        playerDocument.setAge(player.age());
        playerDocument.setNationalities(player.nationality());
        playerDocument.setCurrentMarketValue(TransfermarktValueMapper.map(player.marketValue()));

        return playerDocument;
    }
}
