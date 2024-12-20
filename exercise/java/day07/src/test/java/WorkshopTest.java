import org.junit.jupiter.api.Test;
import workshop.Gift;
import workshop.Status;
import workshop.Workshop;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WorkshopTest {
    private static final String TOY_NAME = "1 Super Nintendo";

    @Test
    void completingAGiftShouldSetItsStatusToProduced() {
        var workshop = new Workshop();
        workshop.addGift(new Gift(TOY_NAME));

        var optionalGift = workshop.completeGift(TOY_NAME);

        assertGiftCompleted(optionalGift);
    }

    @Test
    void completingANonExistingGiftShouldReturnEmpty() {
        var workshop = new Workshop();

        var optionalGift = workshop.completeGift("NonExistingToy");

        assertNoGiftCompleted(optionalGift);
    }

    private static void assertGiftCompleted(Optional<Gift> completedGift) {
        assertTrue(completedGift.isPresent());
        assertEquals(Status.PRODUCED, completedGift.get().status());
    }

    private static void assertNoGiftCompleted(Optional<Gift> completedGift) {
        assertFalse(completedGift.isPresent());
    }
}
