package deny.football.data.quiz;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SingleChoiceQuestionTest {

    @Test
    void resolveShouldReturnTrueOnCorrectAnswer() {
        var correctAnswer = new Answer<>(1L, "answer 1");
        var incorrectAnswer = new Answer<>(2L, "answer 2");
        var question = new SingleChoiceQuestion<>(Set.of(correctAnswer, incorrectAnswer), correctAnswer.id());

        var result = question.resolve(correctAnswer.id());

        assertTrue(result);
    }

    @Test
    void resolveShouldReturnFalseOnIncorrectAnswer() {
        var correctAnswer = new Answer<>(1L, "answer 1");
        var incorrectAnswer = new Answer<>(2L, "answer 2");
        var incorrectAnswer2 = new Answer<>(3L, "answer 3");
        var question = new SingleChoiceQuestion<>(Set.of(correctAnswer, incorrectAnswer, incorrectAnswer2), correctAnswer.id());

        var result = question.resolve(incorrectAnswer.id());
        var result2 = question.resolve(incorrectAnswer2.id());

        assertFalse(result);
        assertFalse(result2);
    }
}
