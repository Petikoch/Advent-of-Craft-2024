import communication.Configuration;
import communication.Reindeer;
import communication.SantaCommunicator;
import doubles.TestLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SantaCommunicatorTest {
    private static final String DASHER = "Dasher";
    private static final String NORTH_POLE = "North Pole";
    private final int numberOfDaysToRest = 2;
    private final int numberOfDaysBeforeChristmas = 24;
    private final TestLogger logger = new TestLogger();
    private SantaCommunicator communicator;

    @BeforeEach
    void setup() {
        this.communicator = new SantaCommunicator(new Configuration(numberOfDaysToRest, numberOfDaysBeforeChristmas));
    }

    @Test
    void composeMessage() {
        var message = communicator.composeMessage(
                reindeer(5)
        );
        assertThat(message).isEqualTo("Dear Dasher, please return from North Pole in 17 day(s) to be ready and rest before Christmas.");
    }

    @Test
    void shouldDetectOverdueReindeer() {
        var overdue = communicator.isOverdue(
                reindeer(numberOfDaysBeforeChristmas),
                logger);

        assertThat(overdue).isTrue();
        assertThat(logger.getLog())
                .isEqualTo("Overdue for Dasher located North Pole.");
    }

    @Test
    void shouldReturnFalseWhenNoOverdue() {
        assertThat(
                communicator.isOverdue(
                        reindeer(numberOfDaysBeforeChristmas - numberOfDaysToRest - 1),
                        logger)
        ).isFalse();
    }

    private static Reindeer reindeer(int numbersOfDaysForComingBack) {
        return new Reindeer(DASHER, NORTH_POLE, numbersOfDaysForComingBack);
    }
}