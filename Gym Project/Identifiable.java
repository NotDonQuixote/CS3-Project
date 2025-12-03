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
 * Interface for identifiable entities in the gym system.
 * Ensures that all identifiable objects can return a unique username.
 *
 * @author Team 4
 * @version 2.0
 */
public interface Identifiable {

    /**
     * Gets the username of the identifiable entity.
     *
     * @return The unique username
     */
    String getUsername();
}
