package communication;

public class SantaCommunicator {

    private final Configuration configuration;

    public SantaCommunicator(Configuration configuration) {
        this.configuration = configuration;
    }

    public String composeMessage(Reindeer reindeer) {
        var daysBeforeReturn = daysBeforeReturn(reindeer.numbersOfDaysForComingBack());

        return "Dear " + reindeer.name() + ", please return from " + reindeer.location() +
               " in " + daysBeforeReturn + " day(s) to be ready and rest before Christmas.";
    }

    public boolean isOverdue(Reindeer reindeer, Logger logger) {
        if (daysBeforeReturn(reindeer.numbersOfDaysForComingBack()) <= 0) {
            logger.log("Overdue for " + reindeer.name() + " located " + reindeer.location() + ".");
            return true;
        }
        return false;
    }

    private int daysBeforeReturn(int numbersOfDaysForComingBack) {
        return configuration.numberOfDaysBeforeChristmas() - numbersOfDaysForComingBack - configuration.numberOfDaysToRest();
    }
}