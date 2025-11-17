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
