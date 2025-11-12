import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthUsernameTakenTest {

    @Test
    void usernameTaken_basic() {
        DataStore.memberCount = 0;
        DataStore.trainerCount = 0;
        DataStore.adminCount = 0;

        Member m = new Member();
        m.username = "alice";
        DataStore.members[DataStore.memberCount++] = m;

        assertTrue(Auth.usernameTaken("alice"));
        assertFalse(Auth.usernameTaken("bob"));
    }
}
