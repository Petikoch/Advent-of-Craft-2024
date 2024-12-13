package routine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoutineTests {

    @Test
    void startRoutineWithMockito() {
        var emailServiceDouble = mock(EmailService.class);
        var scheduleServiceDouble = mock(ScheduleService.class);
        var reindeerFeederDouble = mock(ReindeerFeeder.class);

        var routine = new Routine(
                emailServiceDouble,
                scheduleServiceDouble,
                reindeerFeederDouble
        );
        routine.start();

        var inOrder = inOrder(emailServiceDouble, scheduleServiceDouble, reindeerFeederDouble);
        inOrder.verify(scheduleServiceDouble).todaySchedule();
        inOrder.verify(scheduleServiceDouble).organizeMyDay(any());
        inOrder.verify(reindeerFeederDouble).feedReindeers();
        inOrder.verify(emailServiceDouble).readNewEmails();
        inOrder.verify(scheduleServiceDouble).continueDay();
        verifyNoMoreInteractions(emailServiceDouble, scheduleServiceDouble, reindeerFeederDouble);
    }

    @Test
    void startRoutineWithManualTestDoubles() {
        var emailServiceDouble = new FakeEmailService();
        var scheduleServiceDouble = new FakeScheduleService();
        var reindeerFeederDouble = new FakeReindeerFeeder();

        var routine = new Routine(
                emailServiceDouble,
                scheduleServiceDouble,
                reindeerFeederDouble
        );
        routine.start();

        assertTrue(scheduleServiceDouble.dayOrganized);
        assertTrue(reindeerFeederDouble.reindeersFed);
        assertTrue(emailServiceDouble.emailsRead);
        assertTrue(scheduleServiceDouble.dayContinued);
    }

    public class FakeEmailService implements EmailService {
        boolean emailsRead = false;

        @Override
        public void readNewEmails() {
            emailsRead = true;
        }
    }

    public class FakeScheduleService implements ScheduleService {
        private boolean dayOrganized = false;
        private boolean dayContinued = false;

        @Override
        public Schedule todaySchedule() {
            return new Schedule();
        }

        @Override
        public void organizeMyDay(Schedule schedule) {
            this.dayOrganized = true;
        }

        @Override
        public void continueDay() {
           this.dayContinued = true;
        }
    }

    public class FakeReindeerFeeder implements ReindeerFeeder {

        boolean reindeersFed = false;

        @Override
        public void feedReindeers() {
            reindeersFed = true;
        }
    }
}