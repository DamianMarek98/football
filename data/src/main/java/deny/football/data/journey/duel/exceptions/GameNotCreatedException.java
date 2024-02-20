package deny.football.data.journey.duel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Game not created")
public class GameNotCreatedException extends RuntimeException {
}
