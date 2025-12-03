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

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Utility class for logging user activities.
 * Records all significant actions performed by users in the system
 * to a log file for audit trail and debugging purposes.
 *
 * @author Team 4
 * @version 2.0
 */
public class ActivityLogger {

    /**
     * Logs a user activity with a timestamp.
     * Appends the log entry to ActivityLog.txt file.
     *
     * @param username The username of the user performing the action
     * @param action   The action being logged
     */
    public static void log(String username, String action) {
        try (FileWriter writer = new FileWriter("ActivityLog.txt", true)) {
            String time = LocalDateTime.now().toString();
            writer.write(time + " - " + username + " " + action + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Could not write to log file.");
        }
    }
}
