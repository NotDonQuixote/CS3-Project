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
 * Central data storage for the gym management system.
 * Uses static arrays to store all users (members, trainers, admins),
 * sessions, and membership plans.
 * Acts as an in-memory database accessible throughout the application.
 *
 * @author Team 4
 * @version 2.0
 */
public class DataStore {

    /** Array storing all members in the system */
    public static Member[] members = new Member[100];

    /** Current count of members */
    public static int memberCount = 0;

    /** Array storing all trainers in the system */
    public static Trainer[] trainers = new Trainer[100];

    /** Current count of trainers */
    public static int trainerCount = 0;

    /** Array storing all administrators in the system */
    public static Administrator[] admins = new Administrator[1];

    /** Current count of administrators */
    public static int adminCount = 0;

    /** Array storing all workout sessions */
    public static WorkoutSession[] sessions = new WorkoutSession[200];

    /** Current count of workout sessions */
    public static int sessionCount = 0;

    /** Array storing all membership plans */
    public static MembershipPlan[] plans = new MembershipPlan[50];

    /** Current count of membership plans */
    public static int planCount = 0;
}
