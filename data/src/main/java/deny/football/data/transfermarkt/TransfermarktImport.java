package deny.football.data.transfermarkt;

import deny.football.data.infrastructure.ClubRepository;
import deny.football.data.infrastructure.PlayerDocument;
import deny.football.data.infrastructure.PlayerRepository;
import deny.football.data.logic.ClubsCreator;
import deny.football.data.logic.PlayerDocumentFactory;
import deny.football.data.transfermarkt.dto.Competition;
import deny.football.data.transfermarkt.dto.PlayerProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


@Service
public class TransfermarktImport {
    Logger logger = LoggerFactory.getLogger(TransfermarktImport.class);
    private final TransfermarktFacade transfermarktFacade;
    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final ClubsCreator clubsCreator;

    @Autowired
    public TransfermarktImport(TransfermarktFacade transfermarktFacade, PlayerRepository playerRepository, ClubRepository clubRepository, ClubsCreator clubsCreator) {
        this.transfermarktFacade = transfermarktFacade;
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
        this.clubsCreator = clubsCreator;
    }

    @Async
    public void importPlayers(String leagueId) {
        logger.info("Importing players...");
        var competition = transfermarktFacade.getCompetitionClubs(leagueId);
        createClubDocuments(competition);
        var players = competition.clubs().stream()
                .flatMap(club -> transfermarktFacade.getPlayers(club.id()).stream())
                .filter(player -> !playerRepository.existsById(player.id()))
                .toList();

        logger.info("{} players to import!", players.size());
        final ThreadFactory threadFactory = Thread.ofVirtual().name("routine-", 0).factory();
        try (var executor = Executors.newThreadPerTaskExecutor(threadFactory)) {
            for (var player : players) {
                logger.info("Player: {} import started", player.name());
                var playerProfileFuture = executor.submit(() -> {
                    PlayerProfile profile = transfermarktFacade.getPlayerProfile(player.id());
                    logger.info("player profile imported");
                    return profile;
                });
                var transfersFuture = executor.submit(() -> {
                    var transfers = transfermarktFacade.getMarketTransfers(player.id());
                    logger.info("player transfers imported");
                    return transfers;
                });
                var markerValuesFuture = executor.submit(() -> {
                    var values = transfermarktFacade.getMarketValueHistory(player.id());
                    logger.info("player values imported");
                    return values;
                });
                logger.info("Imported player: {}", playerProfileFuture.get().name());
                playerRepository.save(PlayerDocumentFactory.create(player, playerProfileFuture.get().imageURL(), transfersFuture.get(), markerValuesFuture.get()));
            }
        } catch(InterruptedException | ExecutionException e){
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
        }
    }

    private void createClubDocuments(Competition competition) {
        logger.info("Competition obtained");
        var clubProfiles = competition.clubs()
                .stream()
                .filter(club -> !clubRepository.existsById(club.id()))
                .map(club -> transfermarktFacade.getClubProfile(club.id()))
                .toList();
        clubsCreator.createClubs(clubProfiles);
        logger.info("{} clubs created", clubProfiles.size());
    }
}
