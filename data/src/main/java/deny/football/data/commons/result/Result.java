package deny.football.data.commons.result;

import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Optional;

public class Result {
    final Pair<Optional<Failure>, Optional<Success>> resultPair;

    private Result(Pair<Optional<Failure>, Optional<Success>> result) {
        this.resultPair = result;
    }

    public static Result success(List<DomainEvent> events) {
        return new Result(Pair.of(Optional.empty(), Optional.of(new Success(events))));
    }

    public static Result success(DomainEvent event) {
        return new Result(Pair.of(Optional.empty(), Optional.of(new Success(List.of(event)))));
    }

    public static Result success() {
        return new Result(Pair.of(Optional.empty(), Optional.of(new Success(List.of()))));
    }

    public static Result failure(String message) {
        return new Result(Pair.of(Optional.of(new Failure(message)), Optional.empty()));
    }

    public boolean isSuccessful() {
        return resultPair.getSecond().isPresent();
    }

    public boolean isFailure() {
        return resultPair.getFirst().isPresent();
    }
}

record Success(List<DomainEvent> events) {}

record Failure(String message) {}
