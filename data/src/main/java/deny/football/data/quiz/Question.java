package deny.football.data.quiz;

import java.util.Set;

interface Question<T, A> {
    Set<Answer<T>> getAnswers();

    boolean resolve(A answer);
}
