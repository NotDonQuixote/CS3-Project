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

import java.util.ArrayList;

/**
 * Represents a workout session offered by the gym.
 * Contains session details including capacity, timing, and enrolled members.
 * Uses composition with ArrayList to manage member enrollments.
 */
public class WorkoutSession {
    String sessionId;
    String sessionName;
    String type;
    String date;
    String time;
    int capacity;
    String trainerUsername;
    ArrayList<String> members = new ArrayList<> ();

    /**
     * Adds a member to this session if there is available capacity.
     * Uses composition to manage the collection of enrolled members.
     *
     * @param person The username of the member to add
     */
    public void addMember(String person){
        if (capacity > members.size ()){
            members.add(person);
        }
        else{
            System.out.println ("This session is at max capacity.");
        }
    }

    /**
     * Finds the trainer's full name by looking up their username in the system.
     *
     * @return The trainer's first name, or null if not found
     */
    public String findTrainer(){
        //find the trainer by their username and return the Trainer's name on the client side
        for (int i = 0; i < DataStore.trainerCount; i++) {
            if (DataStore.trainers[i].username.equals (trainerUsername)) {
                return(DataStore.trainers[i].firstName);
            }
        }
        return(null);
    }
}
