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

public class Person {
    //TODO: make Customers and Trainers a subclass of People so you can add end dates to customers, and specialties to trainers
    protected int customerId;
    String firstName;
    String lastName;
    String username;
    String password;

//Customers and Members mean the same thing
    class Customer extends Person{
        String startDate;
        String endDate;
        int membership;

    }
    class Staff extends Person{
        String specialty;
    }
}