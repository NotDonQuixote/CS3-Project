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
 * Authentication and user lookup helper methods for members, trainers, and admins.
 * Provides static methods for user registration, login, searching, and deletion.
 */
public class Auth {

    /**
     * Checks if a username is already taken by any user in the system.
     *
     * @param u The username to check
     * @return true if the username is already taken, false otherwise
     */
    public static boolean usernameTaken(String u) {
        for (int i = 0; i < DataStore.memberCount; i++) {
            if (DataStore.members[i].username.equals(u)) return true;
        }
        for (int i = 0; i < DataStore.trainerCount; i++) {
            if (DataStore.trainers[i].username.equals(u)) return true;
        }
        for (int i = 0; i < DataStore.adminCount; i++) {
            if (DataStore.admins[i].username.equals(u)) return true;
        }
        return false;
    }

    /**
     * Registers a new member in the system.
     *
     * @param u The username for the new member
     * @param p The password for the new member
     * @param f The first name
     * @param l The last name
     */
    public static void registerMember(String u, String p, String f, String l) {
        Member m = new Member();
        m.username = u;
        m.password = p;
        m.firstName = f;
        m.lastName = l;
        DataStore.members[DataStore.memberCount] = m;
        DataStore.memberCount++;
    }

    /**
     * Registers a new trainer in the system.
     *
     * @param u The username for the new trainer
     * @param p The password for the new trainer
     * @param f The first name
     * @param l The last name
     * @param s The trainer's specialty
     */
    public static void registerTrainer(String u, String p, String f, String l, String s) {
        Trainer t = new Trainer();
        t.username = u;
        t.password = p;
        t.firstName = f;
        t.lastName = l;
        t.specialty = s;
        DataStore.trainers[DataStore.trainerCount] = t;
        t.trainerID = DataStore.trainerCount;
        DataStore.trainerCount++;
    }

    /**
     * Authenticates a user by username and password.
     * Searches through members, trainers, and admins.
     *
     * @param u The username
     * @param p The password
     * @return The Person object if credentials are valid, null otherwise
     */
    public static Person login(String u, String p) {
        for (int i = 0; i < DataStore.memberCount; i++) {
            Member m = DataStore.members[i];
            if (m.username.equals(u) && m.password.equals(p)) return m;
        }
        for (int i = 0; i < DataStore.trainerCount; i++) {
            Trainer t = DataStore.trainers[i];
            if (t.username.equals(u) && t.password.equals(p)) return t;
        }
        for (int i = 0; i < DataStore.adminCount; i++) {
            Administrator a = DataStore.admins[i];
            if (a.username.equals(u) && a.password.equals(p)) return a;
        }
        return null;
    }

    /**
     * Finds a member by username, first name, or last name.
     *
     * @param key The search key (username, first name, or last name)
     * @return The Member if found, null otherwise
     */
    public static Member findMember(String key) {
        // Assumption: we treat username as the ID,
        // and also allow search by first or last name.
        for (int i = 0; i < DataStore.memberCount; i++) {
            Member m = DataStore.members[i];
            if (m.username.equals(key) ||
                    m.firstName.equals(key) ||
                    m.lastName.equals(key)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Deletes a member from the system.
     *
     * @param u The username of the member to delete
     * @return true if deletion was successful, false if member not found
     */
    public static boolean deleteMember(String u) {
        for (int i = 0; i < DataStore.memberCount; i++) {
            if (DataStore.members[i].username.equals(u)) {
                DataStore.members[i] = DataStore.members[DataStore.memberCount - 1];
                DataStore.members[DataStore.memberCount - 1] = null;
                DataStore.memberCount--;
                return true;
            }
        }
        return false;
    }
}
