package deny.football.data.quiz;

import java.util.Objects;

record Answer<T>(Long id, T value) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer<?> answer = (Answer<?>) o;
        return Objects.equals(id, answer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
