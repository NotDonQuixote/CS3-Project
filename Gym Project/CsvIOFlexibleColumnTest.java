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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * JUnit test for CsvIO flexible column handling.
 * Tests that CSV files with columns in different orders are parsed correctly.
 */
public class CsvIOFlexibleColumnTest {

    @Test
    public void testLoadUsersWithDifferentColumnOrder() throws IOException {
        // Reset data store
        DataStore.memberCount = 0;
        DataStore.trainerCount = 0;
        DataStore.adminCount = 0;

        // Create a test CSV file with columns in different order
        String testFilePath = "test_users.csv";
        FileWriter fw = new FileWriter(testFilePath);
        fw.write("User Type,Password,Last Name,First Name,Username,ID,Speciality,Membership,Start Date,End Date\n");
        fw.write("Member,pass123,Doe,John,johndoe,1,,Premium,2025-01-01,2025-12-31\n");
        fw.write("Trainer,pass456,Smith,Jane,janesmith,2,Weightlifting,,,\n");
        fw.close();

        // Load the CSV
        CsvIO.loadUsers(testFilePath);

        // Verify the data was loaded correctly despite different column order
        assertEquals(1, DataStore.memberCount);
        assertEquals(1, DataStore.trainerCount);

        Member m = DataStore.members[0];
        assertEquals("johndoe", m.username);
        assertEquals("John", m.firstName);
        assertEquals("Doe", m.lastName);
        assertEquals("Premium", m.membership);

        Trainer t = DataStore.trainers[0];
        assertEquals("janesmith", t.username);
        assertEquals("Jane", t.firstName);
        assertEquals("Smith", t.lastName);
        assertEquals("Weightlifting", t.specialty);

        // Clean up
        Files.deleteIfExists(Paths.get(testFilePath));
    }

    @Test
    public void testSaveAndReloadUsers() throws IOException {
        // Reset data store
        DataStore.memberCount = 0;
        DataStore.trainerCount = 0;
        DataStore.adminCount = 0;

        // Create test users
        Member m = new Member();
        m.username = "testmem";
        m.firstName = "Test";
        m.lastName = "Member";
        m.password = "test123";
        m.membership = "Basic";
        m.startDate = "2025-01-01";
        m.endDate = "2025-12-31";
        DataStore.members[DataStore.memberCount++] = m;

        // Save to CSV
        String testFilePath = "test_save_users.csv";
        CsvIO.saveUsers(testFilePath);

        // Reload users
        DataStore.memberCount = 0;
        CsvIO.loadUsers(testFilePath);

        // Verify reloaded data
        assertEquals(1, DataStore.memberCount);
        Member reloaded = DataStore.members[0];
        assertEquals("testmem", reloaded.username);
        assertEquals("Test", reloaded.firstName);
        assertEquals("Basic", reloaded.membership);

        // Clean up
        Files.deleteIfExists(Paths.get(testFilePath));
    }

    @Test
    public void testLoadSessionsWithDifferentColumnOrder() throws IOException {
        // Reset data store
        DataStore.sessionCount = 0;
        DataStore.trainerCount = 0;

        // Create a trainer first
        Trainer t = new Trainer();
        t.username = "trainer1";
        t.trainerID = 1;
        t.customerId = 1;
        DataStore.trainers[DataStore.trainerCount++] = t;

        // Create a test CSV file with columns in different order
        String testFilePath = "test_sessions.csv";
        FileWriter fw = new FileWriter(testFilePath);
        fw.write("Trainer ID,End Time,Start Time,Date,Capacity,Type,ID\n");
        fw.write("1,11:00,10:00,2025-12-05,20,Yoga,s1\n");
        fw.close();

        // Load the CSV
        CsvIO.loadSessions(testFilePath);

        // Verify the data was loaded correctly despite different column order
        assertEquals(1, DataStore.sessionCount);

        WorkoutSession s = DataStore.sessions[0];
        assertEquals("s1", s.sessionId);
        assertEquals("Yoga", s.type);
        assertEquals(20, s.capacity);
        assertEquals("2025-12-05", s.date);
        assertEquals("trainer1", s.trainerUsername);

        // Clean up
        Files.deleteIfExists(Paths.get(testFilePath));
    }
}
