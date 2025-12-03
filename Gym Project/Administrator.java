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

/**
 * Represents a gym administrator.
 * Extends the Person class and provides administrative capabilities.
 * Admins can manage members, trainers, sessions, and membership plans.
 *
 * @author Team 4
 * @version 2.0
 */
public class Administrator extends Person implements Identifiable {

    /**
     * Returns the administrator's username.
     *
     * @return The admin's username
     */
    public String getUsername() { return username; }
}
