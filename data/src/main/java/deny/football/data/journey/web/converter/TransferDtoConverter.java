package deny.football.data.journey.web.converter;

import deny.football.data.commons.exceptions.ElementNotFoundException;
import deny.football.data.infrastructure.*;
import deny.football.data.journey.web.dto.TransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TransferDtoConverter implements Converter<Long, List<TransferDto>> {
    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public TransferDtoConverter(ClubRepository clubRepository, PlayerRepository playerRepository) {
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public List<TransferDto> convert(@NonNull Long playerId) {
        var playerDocument = playerRepository.findById(playerId)
                .orElseThrow(() -> new ElementNotFoundException(PlayerDocument.class, playerId));

        return convert(playerDocument.getTransfers());
    }

    private List<TransferDto> convert(List<Transfer> transfers) {
        Set<Long> clubIds = transfers.stream()
                .flatMap(transfer -> Stream.of(transfer.getClubToId(), transfer.getClubFromId()))
                .collect(Collectors.toSet());
        var clubIdImageMap = clubRepository.findAllById(clubIds).stream()
                .collect(Collectors.toMap(ClubDocument::getId, ClubDocument::getImage));

        return transfers.stream()
                .map(transfer -> {
                    String clubFromImage = clubIdImageMap.get(transfer.getClubFromId());
                    String clubToImage = clubIdImageMap.get(transfer.getClubToId());
                    return convert(transfer, clubFromImage, clubToImage);
                })
                .toList();
    }

    private TransferDto convert(Transfer transfer, String clubFromImage, String clubToImage) {
        return new TransferDto(transfer.getClubFromId(),
                transfer.getClubFromName(),
                clubFromImage,
                transfer.getClubToId(),
                transfer.getClubToName(),
                clubToImage,
                transfer.getDate(),
                transfer.getUpcoming(),
                transfer.getSeason(),
                transfer.getFee());
    }
}
