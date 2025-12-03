import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthLoginTest.class,
        AuthUsernameTakenTest.class,
        CsvIOFlexibleColumnTest.class,
        MemberPlanTest.class,
        PlanCrudTest.class,
        SessionEnrollmentTest.class
})
public class GymTestSuite {
}
