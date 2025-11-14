import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Error: java: package or.junit.jupiter.api does not exist
//My IDE doesn't seem to have this dependency, and I'm trying to find it to get it working -Dante

public class AuthLoginTest {

    @Test
    void login_member_admin() {
        DataStore.memberCount = 0;
        DataStore.trainerCount = 0;
        DataStore.adminCount = 0;

        Member m = new Member();
        m.username = "mem1";
        m.password = "p1";
        DataStore.members[DataStore.memberCount++] = m;

        Administrator a = new Administrator();
        a.username = "admin";
        a.password = "admin";
        DataStore.admins[DataStore.adminCount++] = a;

        assertTrue(Auth.login("mem1","p1") instanceof Member);
        assertTrue(Auth.login("admin","admin") instanceof Administrator);
        assertNull(Auth.login("x","y"));
    }
}
