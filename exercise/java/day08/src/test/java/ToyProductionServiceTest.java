import domain.Toy;
import doubles.InMemoryToyRepository;
import org.junit.jupiter.api.Test;
import service.ToyProductionService;

import java.util.Optional;

import static domain.Toy.State.IN_PRODUCTION;
import static org.assertj.core.api.Assertions.assertThat;

class ToyProductionServiceTest {
    private static final String TOY_NAME = "Train";

    @Test
    void assignToyToElfShouldPassTheItemInProduction() {
        var repository = new InMemoryToyRepository();
        var service = new ToyProductionService(repository);
        repository.save(new Toy(TOY_NAME));

        service.assignToyToElf(TOY_NAME);

        Optional<Toy> optionalToy = repository.findByName(TOY_NAME);
        assertThat(optionalToy).isPresent();
        assertThat(optionalToy.get().getState()).isEqualTo(IN_PRODUCTION);
    }
}