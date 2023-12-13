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
    private final ClubsCreator clubsCreator;

    @Autowired
    public TransfermarktImport(TransfermarktFacade transfermarktFacade, PlayerRepository playerRepository, ClubsCreator clubsCreator) {
        this.transfermarktFacade = transfermarktFacade;
        this.playerRepository = playerRepository;
        this.clubsCreator = clubsCreator;
    }

    @Async
    @Transactional
    public void importPlayers(String leagueId) {
        logger.info("Importing players...");
        var competition = transfermarktFacade.getCompetitionClubs(leagueId);
        createClubDocuments(competition);

        final ThreadFactory threadFactory = Thread.ofVirtual().name("routine-", 0).factory();
        try (var executor = Executors.newThreadPerTaskExecutor(threadFactory)) {
            var players = competition.clubs().stream()
                    .flatMap(club -> transfermarktFacade.getPlayers(club.id()).stream())
                    .map(player -> {
                        try {
                            logger.info("1 second delay");
                            Thread.sleep(1000);
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
                            return PlayerDocumentFactory.create(player, playerProfileFuture.get().imageURL(), transfersFuture.get(), markerValuesFuture.get());
                        } catch (InterruptedException | ExecutionException e) {
                            Thread.currentThread().interrupt();
                            logger.warn("Failed to import player: {}", player.name());
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();
            playerRepository.saveAll(players);
        }
    }

    private void createClubDocuments(Competition competition) {
        logger.info("Competition obtained");
        var clubProfiles = competition.clubs()
                .stream()
                .map(club -> transfermarktFacade.getClubProfile(club.id()))
                .toList();
        clubsCreator.createClubs(clubProfiles);
        logger.info("Clubs created");
    }
}
