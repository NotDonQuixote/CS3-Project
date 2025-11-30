/*
 * Team 4
 * Name: Christian Lopez-Matulessy, Dante Morales, Cesar Trevizo
 * Date: 11/16/2025
 * Course: CS 3331 – Advanced Object-Oriented Programming
 * Instructor: Dr. Bhanukiran Gurijala
 * Project Part 1 - Gym Management System
 * Honesty Statement: We completed this work entirely on our own
 * without any outside sources, including peers,
 * experts, or online sources.
 */

public class DataStore {
    public static Member[] members = new Member[100];
    public static int memberCount = 0;

    public static Trainer[] trainers = new Trainer[100];
    public static int trainerCount = 0;

    public static Administrator[] admins = new Administrator[1];
    public static int adminCount = 0;

    public static WorkoutSession[] sessions = new WorkoutSession[200];
    public static int sessionCount = 0;

    public static MembershipPlan[] plans = new MembershipPlan[50];
    public static int planCount = 0;
}
