/*
 * Team 4
 * Name: Christian Lopez-Matulessy, Dante Morales, Cesar Trevizo
 * Date: 11/16/2025
 * Course: CS 3331 – Advanced Object-Oriented Programming
 * Instructor: Dr. Bhanukiran Gurijala
 * Project Part 1 - Gym Management System
 * Honesty Statement: We completed this work entirely on our own
 * without any outside sources, including peers,
 * experts, or online sources.
 */

// Was having some problems with this IDE with Junit 5 so for now decided to switch to Junit4
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class AuthLoginTest {

    @Test
    public void login_member_admin() throws GymException {
        // reset datastore
        DataStore.memberCount = 0;
        DataStore.trainerCount = 0;
        DataStore.adminCount = 0;

        // set up a member
        Member m = new Member();
        m.username = "mem1";
        m.password = "p1";
        DataStore.members[DataStore.memberCount++] = m;

        // set up an admin
        Administrator a = new Administrator();
        a.username = "admin";
        a.password = "admin";
        DataStore.admins[DataStore.adminCount++] = a;

        // valid logins should return the right type (no exception thrown)
        assertTrue(Auth.login("mem1", "p1") instanceof Member);
        assertTrue(Auth.login("admin", "admin") instanceof Administrator);

        // invalid login should now THROW GymException instead of returning null
        assertThrows(GymException.class, () -> Auth.login("x", "y"));
    }
}
