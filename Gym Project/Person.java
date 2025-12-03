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
 * Represents a person in the gym management system.
 * Base class for all users: Members, Trainers, and Administrators.
 * Uses inheritance to share common attributes and behavior.
 *
 * @author Team 4
 * @version 2.0
 */
public abstract class Person {
    //TODO: make Customers and Trainers a subclass of People so you can add end dates to customers, and specialties to trainers

    /** Unique identifier for the person in the system */
    protected int customerId;

    /** Person's first name */
    String firstName;

    /** Person's last name */
    String lastName;

    /** Unique username for authentication */
    String username;

    /** Password for authentication */
    String password;

}