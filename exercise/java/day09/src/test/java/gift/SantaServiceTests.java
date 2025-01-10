package gift;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SantaServiceTests {

    private final SantaService service = new SantaService();

    @Test
    void requestIsApprovedForNiceChildWithFeasibleGift() {
        Child niceChild = ChildBuilder
                .aChild()
                .nice()
                .build();
        assertThat(service.evaluateRequest(niceChild)).isTrue();
    }

    @Test
    void requestIsDeniedForNaughtyChild() {
        Child naughtyChild = ChildBuilder
                .aChild()
                .naughty()
                .build();
        assertThat(service.evaluateRequest(naughtyChild)).isFalse();
    }

    @Test
    void requestIsDeniedForNiceChildWithInfeasibleGift() {
        Child niceChildWithInfeasibleGift =
                ChildBuilder
                        .aChild()
                        .nice()
                        .infeasibleGift()
                        .build();
        assertThat(service.evaluateRequest(niceChildWithInfeasibleGift)).isFalse();
    }

    private static class ChildBuilder {

        private Behavior behavior = null;
        private boolean isFeasible = true;

        public static ChildBuilder aChild() {
            return new ChildBuilder();
        }

        public ChildBuilder nice() {
            behavior = Behavior.NICE;
            return this;
        }

        public ChildBuilder naughty() {
            behavior = Behavior.NAUGHTY;
            return this;
        }

        public ChildBuilder infeasibleGift() {
            isFeasible = false;
            return this;
        }

        public Child build() {
            return new Child("AnyFirstName", "AnyLastName", 10, behavior, new GiftRequest("AnyToy", isFeasible, Priority.DREAM));
        }
    }
}