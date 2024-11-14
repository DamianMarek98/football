package deny.football.data.quiz;

import java.util.List;

interface Question {
    List<Answer<?>> getAnswers();

    boolean resolve(Long answerId);
}
