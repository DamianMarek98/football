package deny.football.data.quiz;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SingleChoiceQuestionTest {

    @Test
    void resolveShouldReturnTrueOnCorrectAnswer() {
        var correctAnswerId = 1L;
        var question = new SingleChoiceQuestion(List.of(), correctAnswerId);

        var result = question.resolve(correctAnswerId);

        assertTrue(result);
    }

    @Test
    void resolveShouldReturnFalseOnIncorrectAnswer() {
        var correctAnswerId = 1L;
        var question = new SingleChoiceQuestion(List.of(), correctAnswerId);

        var result = question.resolve(correctAnswerId + 1);

        assertFalse(result);
    }
}
