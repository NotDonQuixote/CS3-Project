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
 * Custom exception class for gym-related errors.
 * Used to distinguish gym-specific exceptions from generic Java exceptions.
 */
public class GymException extends Exception {
    /**
     * Constructs a new GymException with the specified detail message.
     * 
     * @param message The detail message (which is saved for later retrieval
     *                by the Throwable.getMessage() method).
     */
    public GymException(String message) {
        super(message);
    }

    /**
     * Constructs a new GymException with the specified detail message and cause.
     * 
     * @param message The detail message (which is saved for later retrieval).
     * @param cause   The cause (which is saved for later retrieval by the
     *                Throwable.getCause() method).
     */
    public GymException(String message, Throwable cause) {
        super(message, cause);
    }
}
