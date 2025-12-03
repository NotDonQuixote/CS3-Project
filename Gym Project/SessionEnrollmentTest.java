/*
 * Team 4
 * Name: Christian Lopez-Matulessy, Dante Morales, Cesar Trevizo
 * Date: 12/01/2025
 * Course: CS 3331 – Advanced Object-Oriented Programming
 * Instructor: Dr. Bhanukiran Gurijala
 * Project Part 2 - Gym Management System
 * Honesty Statement: We completed this work entirely on our own
 * without any outside sources, including peers,
 * experts, or online sources.
 */

//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test for member session enrollment functionality.
 * Tests session availability, enrollment, and capacity constraints.
 */
public class SessionEnrollmentTest {

    @Test
    public void testSessionWithAvailableCapacity() {
        // Reset data store
        DataStore.sessionCount = 0;
        DataStore.trainerCount = 0;

        // Create a trainer
        Trainer t = new Trainer();
        t.username = "trainer1";
        t.firstName = "John";
        t.lastName = "Trainer";
        t.trainerID = 1;
        DataStore.trainers[DataStore.trainerCount++] = t;

        // Create a session with capacity
        WorkoutSession s = new WorkoutSession();
        s.sessionId = "s1";
        s.sessionName = "Yoga";
        s.type = "Yoga";
        s.date = "2025-12-05";
        s.time = "10:00-11:00";
        s.capacity = 10;
        s.trainerUsername = "trainer1";
        DataStore.sessions[DataStore.sessionCount++] = s;

        // Verify session has capacity
        assertEquals(10, s.capacity);
        assertEquals(0, s.members.size());
    }

    @Test
    public void testMemberEnrollInSession() {
        // Reset data store
        DataStore.sessionCount = 0;
        DataStore.memberCount = 0;
        DataStore.trainerCount = 0;

        // Create a trainer
        Trainer t = new Trainer();
        t.username = "trainer2";
        t.trainerID = 2;
        DataStore.trainers[DataStore.trainerCount++] = t;

        // Create a session
        WorkoutSession s = new WorkoutSession();
        s.sessionId = "s2";
        s.sessionName = "Pilates";
        s.type = "Pilates";
        s.capacity = 5;
        s.trainerUsername = "trainer2";
        DataStore.sessions[DataStore.sessionCount++] = s;

        // Create a member and enroll
        Member m = new Member();
        m.username = "member1";
        DataStore.members[DataStore.memberCount++] = m;

        // Add member to session
        s.addMember(m.username);

        // Verify enrollment
        assertTrue(s.members.contains(m.username));
        assertEquals(1, s.members.size());
    }

    @Test
    public void testSessionAtCapacity() {
        // Reset data store
        DataStore.sessionCount = 0;

        // Create a session with capacity of 1
        WorkoutSession s = new WorkoutSession();
        s.sessionId = "s3";
        s.capacity = 1;

        // Fill the session
        s.addMember("member1");
        assertEquals(1, s.members.size());

        // Try to add another member (should not exceed capacity)
        int sizeBefore = s.members.size();
        s.addMember("member2");

        // Verify capacity is not exceeded
        assertEquals(sizeBefore, s.members.size());
        assertTrue(s.members.contains("member1"));
        assertFalse(s.members.contains("member2"));
    }
}
