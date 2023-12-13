package deny.football.data.transfermarkt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfermarkt-import")
public class TransfermarktImportController {
    private final TransfermarktImport transfermarktImport;

    @Autowired
    public TransfermarktImportController(TransfermarktImport transfermarktImport) {
        this.transfermarktImport = transfermarktImport;
    }

    @PostMapping("/{leagueId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void importPlayers(@PathVariable String leagueId) {
        transfermarktImport.importPlayers(leagueId);
    }
}
