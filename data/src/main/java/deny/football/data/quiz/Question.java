package deny.football.data.quiz;

import java.util.Set;

interface Question<T> {
    Set<Answer<T>> getAnswers();

    boolean resolve(Long answerId);
}
