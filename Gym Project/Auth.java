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

/**
 * Authentication and user lookup helper methods for members, trainers, and admins.
 */

public class Auth {
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

    public static void registerMember(String u, String p, String f, String l) {
        Member m = new Member();
        m.username = u; m.password = p; m.firstName = f; m.lastName = l;
        DataStore.members[DataStore.memberCount] = m;
        DataStore.memberCount++;
    }

    public static void registerTrainer(String u, String p, String f, String l, String s) {
        Trainer t = new Trainer();
        t.username = u; t.password = p; t.firstName = f; t.lastName = l; t.specialty = s;
        DataStore.trainers[DataStore.trainerCount] = t;
        DataStore.trainerCount++;
    }

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
