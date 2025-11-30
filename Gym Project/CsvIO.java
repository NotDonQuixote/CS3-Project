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

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Utility class for loading and saving users, sessions, and membership plans to CSV files.
 * This version matches the formats:
 *  - GymUsersData.csv : ID,First Name,Last Name,Username,Password,User Type,Membership,Start Date,End Date,Speciality
 *  - GymSessions.csv : ID,Type,Capacity,Date,Start Time,End Time,Trainer ID
 *  - GymPlans.csv    : ID,Plan Name,Duration in Months,Price
 */
public class CsvIO {

    // ====================== USERS ======================

    public static void loadUsers(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);

            // Skip header
            if (sc.hasNextLine()) sc.nextLine();

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] x = line.split(",", -1);
                if (x.length < 6) continue; // must at least have ID, names, username, password, user type

                int id          = parseIntSafe(val(x, 0));
                String first    = val(x, 1);
                String last     = val(x, 2);
                String username = val(x, 3);
                String password = val(x, 4);
                String role     = val(x, 5); // "Member", "Trainer", "Admin"
                String membership = x.length > 6 ? val(x, 6) : "";
                String startDate  = x.length > 7 ? val(x, 7) : "";
                String endDate    = x.length > 8 ? val(x, 8) : "";
                String speciality = x.length > 9 ? val(x, 9) : "";

                if (role.equalsIgnoreCase("ADMIN")) {
                    Administrator a = new Administrator();
                    a.customerId = id;
                    a.firstName = first;
                    a.lastName = last;
                    a.username = username;
                    a.password = password;
                    DataStore.admins[DataStore.adminCount++] = a;

                } else if (role.equalsIgnoreCase("MEMBER")) {
                    Member m = new Member();
                    m.customerId = id;
                    m.firstName = first;
                    m.lastName = last;
                    m.username = username;
                    m.password = password;
                    m.membership = membership;
                    m.startDate = startDate;
                    m.endDate = endDate;
                    DataStore.members[DataStore.memberCount++] = m;

                } else if (role.equalsIgnoreCase("TRAINER")) {
                    Trainer t = new Trainer();
                    t.customerId = id;
                    t.trainerID = id; // keep trainerID in sync with overall ID
                    t.firstName = first;
                    t.lastName = last;
                    t.username = username;
                    t.password = password;
                    t.specialty = speciality;
                    DataStore.trainers[DataStore.trainerCount++] = t;
                }
            }
            sc.close();
        } catch (Exception e) {
            // silently ignore for now, or print if you want:
            // System.out.println("Error loading users: " + e.getMessage());
        }
    }

    public static void saveUsers(String path) {
        try {
            // Ensure every user has an ID; keep existing IDs if present.
            assignUserIds();

            FileWriter w = new FileWriter(path);
            // Header to match GymUsersData.csv
            w.write("ID,First Name,Last Name,Username,Password,User Type,Membership,Start Date,End Date,Speciality\n");

            // Members
            for (int i = 0; i < DataStore.memberCount; i++) {
                Member m = DataStore.members[i];
                w.write(
                        m.customerId + "," +
                                s(m.firstName) + "," +
                                s(m.lastName) + "," +
                                s(m.username) + "," +
                                s(m.password) + "," +
                                "Member," +
                                s(m.membership) + "," +
                                s(m.startDate) + "," +
                                s(m.endDate) + "," +
                                "" + "\n"
                );
            }

            // Trainers
            for (int i = 0; i < DataStore.trainerCount; i++) {
                Trainer t = DataStore.trainers[i];
                w.write(
                        t.customerId + "," +
                                s(t.firstName) + "," +
                                s(t.lastName) + "," +
                                s(t.username) + "," +
                                s(t.password) + "," +
                                "Trainer," +
                                "" + "," + // Membership
                                "" + "," + // Start Date
                                "" + "," + // End Date
                                s(t.specialty) + "\n"
                );
            }

            // Admins
            for (int i = 0; i < DataStore.adminCount; i++) {
                Administrator a = DataStore.admins[i];
                w.write(
                        a.customerId + "," +
                                s(a.firstName) + "," +
                                s(a.lastName) + "," +
                                s(a.username) + "," +
                                s(a.password) + "," +
                                "Admin," +
                                "" + "," + // Membership
                                "" + "," + // Start Date
                                "" + "," + // End Date
                                "" + "\n"  // Speciality
                );
            }

            w.close();
        } catch (Exception e) {
            // System.out.println("Error saving users: " + e.getMessage());
        }
    }

    /**
     * Assign IDs to any users missing one, keeping existing IDs.
     * Also keeps Trainer.trainerID in sync with the user ID.
     */
    private static void assignUserIds() {
        int maxId = 0;

        // First pass: find maximum existing ID
        for (int i = 0; i < DataStore.memberCount; i++) {
            if (DataStore.members[i].customerId > maxId) {
                maxId = DataStore.members[i].customerId;
            }
        }
        for (int i = 0; i < DataStore.trainerCount; i++) {
            if (DataStore.trainers[i].customerId > maxId) {
                maxId = DataStore.trainers[i].customerId;
            }
        }
        for (int i = 0; i < DataStore.adminCount; i++) {
            if (DataStore.admins[i].customerId > maxId) {
                maxId = DataStore.admins[i].customerId;
            }
        }

        // Second pass: assign IDs where missing
        for (int i = 0; i < DataStore.memberCount; i++) {
            if (DataStore.members[i].customerId <= 0) {
                DataStore.members[i].customerId = ++maxId;
            }
        }
        for (int i = 0; i < DataStore.trainerCount; i++) {
            Trainer t = DataStore.trainers[i];
            if (t.customerId <= 0) {
                t.customerId = ++maxId;
            }
            if (t.trainerID <= 0) {
                t.trainerID = t.customerId;
            }
        }
        for (int i = 0; i < DataStore.adminCount; i++) {
            if (DataStore.admins[i].customerId <= 0) {
                DataStore.admins[i].customerId = ++maxId;
            }
        }
    }

    // ====================== SESSIONS ======================

    public static void loadSessions(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);

            // Skip header
            if (sc.hasNextLine()) sc.nextLine();

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] x = line.split(",", -1);
                if (x.length < 7) continue; // ID,Type,Capacity,Date,Start,End,TrainerID

                WorkoutSession s = new WorkoutSession();
                String idStr     = val(x, 0);
                String type      = val(x, 1);
                String capacity  = val(x, 2);
                String date      = val(x, 3);
                String startTime = val(x, 4);
                String endTime   = val(x, 5);
                String trainerId = val(x, 6);

                s.sessionId   = idStr;
                s.type        = type;
                s.sessionName = type; // no separate name in CSV, so use Type as the name
                s.date        = date;
                s.capacity    = parseIntSafe(capacity);
                if (!startTime.isEmpty() || !endTime.isEmpty()) {
                    s.time = startTime + (endTime.isEmpty() ? "" : "-" + endTime);
                } else {
                    s.time = "";
                }

                int tId = parseIntSafe(trainerId);
                s.trainerUsername = findTrainerUsernameById(tId);

                DataStore.sessions[DataStore.sessionCount++] = s;
            }
            sc.close();
        } catch (Exception e) {
            // System.out.println("Error loading sessions: " + e.getMessage());
        }
    }

    public static void saveSessions(String path) {
        try {
            // Make sure trainers have IDs for Trainer ID column
            assignUserIds();

            FileWriter w = new FileWriter(path);
            // Header to match GymSessions.csv
            w.write("ID,Type,Capacity,Date,Start Time,End Time,Trainer ID\n");

            for (int i = 0; i < DataStore.sessionCount; i++) {
                WorkoutSession s = DataStore.sessions[i];

                String idStr = (s.sessionId == null || s.sessionId.isEmpty())
                        ? String.valueOf(i + 1)
                        : s.sessionId;

                String type = (s.type == null || s.type.isEmpty())
                        ? s.sessionName
                        : s.type;

                String date = s(s.date);
                String cap  = String.valueOf(s.capacity);

                String startTime = "";
                String endTime   = "";
                if (s.time != null && !s.time.isEmpty()) {
                    String[] parts = s.time.split("-", 2);
                    startTime = parts[0].trim();
                    if (parts.length > 1) {
                        endTime = parts[1].trim();
                    }
                }

                int trainerId = findTrainerIdByUsername(s.trainerUsername);

                w.write(
                        s(idStr) + "," +
                                s(type) + "," +
                                cap + "," +
                                date + "," +
                                s(startTime) + "," +
                                s(endTime) + "," +
                                (trainerId > 0 ? trainerId : 0) + "\n"
                );
            }
            w.close();
        } catch (Exception e) {
            // System.out.println("Error saving sessions: " + e.getMessage());
        }
    }

    private static String findTrainerUsernameById(int id) {
        if (id <= 0) return "";
        for (int i = 0; i < DataStore.trainerCount; i++) {
            Trainer t = DataStore.trainers[i];
            if (t.trainerID == id || t.customerId == id) {
                return t.username;
            }
        }
        return "";
    }

    private static int findTrainerIdByUsername(String username) {
        if (username == null) return 0;
        for (int i = 0; i < DataStore.trainerCount; i++) {
            Trainer t = DataStore.trainers[i];
            if (username.equals(t.username)) {
                return (t.trainerID > 0) ? t.trainerID : t.customerId;
            }
        }
        return 0;
    }

    // ====================== PLANS ======================

    public static void loadPlans(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);

            // Skip header
            if (sc.hasNextLine()) sc.nextLine();

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] x = line.split(",", -1);
                if (x.length < 4) continue; // ID,Plan Name,Duration in Months,Price

                // x[0] is ID, ignored by logic
                String planName = val(x, 1);
                int duration    = parseIntSafe(val(x, 2));
                double price    = parseDoubleSafe(val(x, 3));

                MembershipPlan p = new MembershipPlan();
                p.planName = planName;
                p.durationMonths = duration;
                p.price = price;

                DataStore.plans[DataStore.planCount++] = p;
            }
            sc.close();
        } catch (Exception e) {
            // System.out.println("Error loading plans: " + e.getMessage());
        }
    }

    public static void savePlans(String path) {
        try {
            FileWriter w = new FileWriter(path);
            // Header to match GymPlans.csv
            w.write("ID,Plan Name,Duration in Months,Price\n");

            for (int i = 0; i < DataStore.planCount; i++) {
                MembershipPlan p = DataStore.plans[i];
                int id = i + 1; // simple sequential ID for plans
                w.write(
                        id + "," +
                                s(p.planName) + "," +
                                p.durationMonths + "," +
                                p.price + "\n"
                );
            }

            w.close();
        } catch (Exception e) {
            // System.out.println("Error saving plans: " + e.getMessage());
        }
    }

    // ====================== helper methods ======================

    static String val(String[] x, int i) {
        if (i < 0 || i >= x.length) return "";
        return x[i].trim();
    }

    static String s(String a) {
        if (a == null) return "";
        return a;
    }

    static int parseIntSafe(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }

    static double parseDoubleSafe(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0.0; }
    }
}
