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
 * Represents a gym trainer.
 * Extends the Person class with trainer-specific attributes like specialty.
 * Trainers can lead workout sessions and are identified by their unique ID.
 *
 * @author Team 4
 * @version 2.0
 */
public class Trainer extends Person implements Identifiable {

    /** The trainer's area of specialization (e.g., Weightlifting, Yoga) */
    String specialty;

    /** Unique identifier for the trainer */
    int trainerID;

    /**
     * Returns the trainer's username.
     *
     * @return The trainer's username
     */
    public String getUsername() { return username; }

    /**
     * Returns the trainer's unique ID.
     *
     * @return The trainer's ID
     */
    public int getTrainerID() {return trainerID;}

    /**
     * Returns the trainer's specialty.
     *
     * @return The trainer's specialty
     */
    public String getSpecialty() {return specialty;}
}
