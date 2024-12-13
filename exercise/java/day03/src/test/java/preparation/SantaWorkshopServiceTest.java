package preparation;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SantaWorkshopServiceTest {
    private static final String RECOMMENDED_AGE = "recommendedAge";
    private final Faker faker = new Faker();
    private SantaWorkshopService service;

    @BeforeEach
    void setUp() {
        service = new SantaWorkshopService();
    }

    @Test
    void prepareGiftWithValidToyShouldInstantiateIt() {
        var gift = prepareGiftFor(aValidWeight());

        assertThat(gift).isNotNull();
    }

    @Test
    void retrieveAttributeOnGift() {
        var gift = prepareGiftFor(aValidWeight());
        gift.addAttribute(RECOMMENDED_AGE, "3");

        assertThat(gift.getRecommendedAge())
                .isEqualTo(3);
    }

    @Test
    void failsForATooHeavyGift() {
        assertThatThrownBy(() -> prepareGiftFor(6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Gift is too heavy for Santa's sleigh");
    }

    private Gift prepareGiftFor(double weight) {
        var giftName = faker.commerce().productName();
        var color = faker.color().name();
        var material = faker.options().option("Plastic", "Wood", "Metal");

        return service.prepareGift(giftName, weight, color, material);
    }

    private double aValidWeight() {
        return faker.number().randomDouble(3, 0, 5);
    }
}
