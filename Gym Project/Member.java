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
 * Represents a gym member.
 * Extends the Person class with additional membership-specific attributes.
 * Implements the Identifiable interface for member identification.
 *
 * @author Team 4
 * @version 2.0
 */
public class Member extends Person implements Identifiable {

    /** Start date of the member's current membership plan */
    String startDate;

    /** End date of the member's current membership plan */
    String endDate;

    /** Name of the member's current membership plan */
    String membership;

    /**
     * Returns the member's username for identification.
     *
     * @return The member's unique username
     */
    public String getUsername() { return username; }
}
