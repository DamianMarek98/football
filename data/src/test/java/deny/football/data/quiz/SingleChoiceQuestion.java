package deny.football.data.quiz;

import java.util.Set;

class SingleChoiceQuestion<T> implements Question<T> {
    private final Set<Answer<T>> answers;
    private final long correctAnswerId;

    SingleChoiceQuestion(Set<Answer<T>> answers, long correctAnswerId) {
        this.answers = answers;
        this.correctAnswerId = correctAnswerId;
    }

    @Override
    public Set<Answer<T>> getAnswers() {
        return answers;
    }

    @Override
    public boolean resolve(Long answerId) {
        return answers.stream()
                .anyMatch(answer -> answer.id().equals(answerId) && answerId.equals(correctAnswerId));
    }
}
