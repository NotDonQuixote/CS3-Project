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
import java.util.ArrayList;

public class WorkoutSession {
    String sessionId;
    String sessionName;
    String type;
    String date;
    String time;
    int capacity;
    String trainerUsername;
    ArrayList<String> members = new ArrayList<> ();

    public void addMember(String person){
        if (capacity > members.size ()){
            members.add(person);
        }
        else{
            System.out.println ("This session is at max capacity.");
        }
    }

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
