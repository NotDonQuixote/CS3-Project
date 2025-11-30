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


import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Main class for the Gym Management System.
 * Loads data from CSV files, shows menus, and saves data on exit.
 */
public class RunGym {
    //main method
    public static void main(String[] args) {
        CsvIO.loadUsers("GymUsersData.csv");
        CsvIO.loadSessions("GymSessions.csv");
        CsvIO.loadPlans("GymPlans.csv");


        Administrator a = new Administrator();
        a.username = "admin";
        a.password = "admin";
        a.firstName = "Admin";
        a.lastName = "User";
        DataStore.admins[0] = a;
        DataStore.adminCount = 1;

        System.out.println ("FOR DEMO: Admin automatically created. ");
        System.out.println ("Admin UserName: " + a.username);
        System.out.println ("Admin Password: " + a.password);

        //Main menu
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1 Register");
            System.out.println("2 Login");
            System.out.println("3 Exit");
            String c = sc.nextLine();

            // allow EXIT or 3 to quit
            if (c.equalsIgnoreCase("EXIT") || c.equals("3")) {
                CsvIO.saveUsers("GymUsersData.csv");
                CsvIO.saveSessions("GymSessions.csv");
                CsvIO.savePlans("GymPlans.csv");
                return;
            }

            switch (c) {
                case "1" -> register(sc);
                case "2" -> login(sc);
                // anything else just loops again
            }
        }
    }

    static void register(Scanner sc) {
        System.out.println("1 Trainer");
        System.out.println("2 Member");
        String r = sc.nextLine();
        System.out.print("first: "); String f = sc.nextLine();
        System.out.print("last: "); String l = sc.nextLine();
        System.out.print("user: "); String u = sc.nextLine();
        if (Auth.usernameTaken(u)) {
            System.out.println("Username already taken. Please choose a different username.");
            return;
        }
        System.out.print("pass: "); String p = sc.nextLine();

        if (r.equals("1")) {
            System.out.print("specialty: "); String s = sc.nextLine();
            Auth.registerTrainer(u, p, f, l, s);
            ActivityLogger.log(u, "registered as trainer");
        } else {
            Auth.registerMember(u, p, f, l);
            ActivityLogger.log(u, "registered as member");
        }
    }

    static void login(Scanner sc) {
        System.out.print("user: "); String u = sc.nextLine();
        System.out.print("pass: "); String p = sc.nextLine();
        Person x = Auth.login(u, p);
        //Checks person type to show the right menu
        if (x instanceof Administrator) {
            ActivityLogger.log(u, "logged in as admin" + x.username);
            adminMenu(sc, u);
        } else if (x instanceof Member) {
            ActivityLogger.log(u, "logged in as member" + x.username);
            memberMenu(sc, (Member) x);
        } else if (x instanceof Trainer) {
            ActivityLogger.log(u, "logged in as trainer" + x.username);
            trainerMenu(sc, x);
        }
    }
//74-443 - Admin functions
    static void adminMenu(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("Logged in as admin: " + (currentUser));
            System.out.println("1 Manage Members");
            System.out.println("2 Manage Trainers");
            System.out.println("3 Manage Admins");
            System.out.println("4 Manage Workout Sessions");
            System.out.println("5 Manage Membership Plans");
            System.out.println("6 Sign Out");
            String c = sc.nextLine();
            switch (c) {
                case "1" -> manageMembers(sc, currentUser);
                case "2" -> manageTrainers(sc, currentUser);
                case "3" -> manageAdmins(sc, currentUser);
                case "4" -> manageSessions(sc, currentUser);
                case "5" -> managePlans(sc, currentUser);
                case "6" -> {
                    return;
                }
            }
        }
    }

    static void manageAdmins(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("Manage Admins");
            System.out.println(" ");
            System.out.println("1 Add");
            System.out.println("2 View");
            System.out.println("3 Update");
            System.out.println("4 Delete");
            System.out.println("5 Back");
            String c = sc.nextLine();
            switch (c) {
                case "1" -> {
                    System.out.print("first: ");
                    String f = sc.nextLine();
                    System.out.print("last: ");
                    String l = sc.nextLine();
                    System.out.print("user: ");
                    String u = sc.nextLine();
                    if (Auth.usernameTaken(u)) continue;
                    System.out.print("pass: ");
                    String p = sc.nextLine();
                    Administrator a = new Administrator();
                    a.firstName = f;
                    a.lastName = l;
                    a.username = u;
                    a.password = p;
                    DataStore.admins[DataStore.adminCount] = a;
                    DataStore.adminCount++;
                    ActivityLogger.log(currentUser, "added admin " + u);
                }
                case "2" -> {
                    System.out.print("ID, name, or username, or ALL: ");
                    String q = sc.nextLine();
                    if (q.equals("ALL")) {
                        for (int i = 0; i < DataStore.adminCount; i++) {
                            System.out.println(DataStore.admins[i].username);
                        }
                    } else {
                        Administrator a = findAdmin(q);
                        if (a != null) System.out.println(a.username);
                        else System.out.println("Admin not found.");
                    }
                }
                case "3" -> {
                    System.out.print("ID, name, or username: ");
                    String u = sc.nextLine();
                    Administrator a = findAdmin(u);
                    if (a == null) {
                        System.out.println("Admin not found.");
                    } else {
                        System.out.println("1 Change Name");
                        System.out.println("2 Change Password");
                        System.out.println("3 Back");
                        String opt = sc.nextLine();
                        if (opt.equals("1")) {
                            System.out.print("first: ");
                            a.firstName = sc.nextLine();
                            System.out.print("last: ");
                            a.lastName = sc.nextLine();
                            ActivityLogger.log(currentUser, "updated admin name " + u);
                        } else if (opt.equals("2")) {
                            System.out.print("new password: ");
                            a.password = sc.nextLine();
                            ActivityLogger.log(currentUser, "changed admin password " + u);
                        }
                    }
                }
                case "4" -> {
                    System.out.print("ID, name, or username: ");
                    String u = sc.nextLine();
                    Administrator a = findAdmin(u);
                    if (a == null) {
                        System.out.println("Admin not found.");
                    } else {
                        System.out.print("Are you sure you want to delete this admin? (Y/N): ");
                        String confirm = sc.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            if (deleteAdmin(u)) {
                                ActivityLogger.log(currentUser, "deleted admin " + u);
                            }
                        } else {
                            System.out.println("Delete cancelled.");
                        }
                    }
                }
                case "5" -> {
                    return;
                }
            }
        }
    }

    static Administrator findAdmin(String key) {
        // Assumption: we treat username as the ID,
        // and also allow search by first or last name.
        for (int i = 0; i < DataStore.adminCount; i++) {
            Administrator a = DataStore.admins[i];
            if (a.username.equals(key) ||
                    a.firstName.equals(key) ||
                    a.lastName.equals(key)) {
                return a;
            }
        }
        return null;
    }

    static boolean deleteAdmin(String u) {
        for (int i = 0; i < DataStore.adminCount; i++) {
            if (DataStore.admins[i].username.equals(u)) {
                DataStore.admins[i] = DataStore.admins[DataStore.adminCount - 1];
                DataStore.admins[DataStore.adminCount - 1] = null;
                DataStore.adminCount--;
                return true;
            }
        }
        return false;
    }

    static void manageMembers(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("Manage Members");
            System.out.println(" ");
            System.out.println("1 Add");
            System.out.println("2 View");
            System.out.println("3 Update");
            System.out.println("4 Delete");
            System.out.println("5 Back");
            String c = sc.nextLine();
            switch (c) {
                //Gonna make this a method that will also call when a member registers -Dante
                case "1" -> {
                    System.out.print("first: ");
                    String f = sc.nextLine();
                    System.out.print("last: ");
                    String l = sc.nextLine();
                    System.out.print("user: ");
                    String u = sc.nextLine();
                    if (Auth.usernameTaken(u)) continue;
                    System.out.print("pass: ");
                    String p = sc.nextLine();
                    Auth.registerMember(u, p, f, l);
                    ActivityLogger.log(currentUser, "added member " + u);
                }
                case "2" -> {
                    System.out.print("ID, name, or username, or ALL: ");
                    String q = sc.nextLine();
                    if (q.equals("ALL")) {
                        for (int i = 0; i < DataStore.memberCount; i++) {
                            System.out.println(DataStore.members[i].username);
                        }
                    } else {
                        Member m = Auth.findMember(q);
                        if (m != null) {
                            System.out.println(m.username);
                        } else {
                            System.out.println("Member not found.");
                        }
                    }
                }

                case "3" -> updateMember(sc, currentUser);
                case "4" -> {
                    System.out.print("ID, name, or username: ");
                    String u = sc.nextLine();
                    Member m = Auth.findMember(u);
                    if (m == null) {
                        System.out.println("Member not found.");
                    } else {
                        System.out.print("Are you sure you want to delete this member? (Y/N): ");
                        String confirm = sc.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            if (Auth.deleteMember(u)) {
                                ActivityLogger.log(currentUser, "deleted member " + u);
                            }
                        } else {
                            System.out.println("Delete cancelled.");
                        }
                    }
                }

                case "5" -> {
                    return;
                }
            }
        }
    }
//Changes the member objects, but hasn't been tested yet
    static void updateMember(Scanner sc, String currentUser) {
        System.out.print("ID, name, or username: ");
        String u = sc.nextLine();
        Member m = Auth.findMember(u);
        if (m == null) {
            System.out.println("Member not found.");
            return;
        }
        while (true) {
            System.out.println("Update Member");
            System.out.println(" ");
            System.out.println("1 Change Name");
            System.out.println("2 Change Username");
            System.out.println("3 Change Password");
            System.out.println("4 Change Membership");
            System.out.println("5 Back");
            String c = sc.nextLine();
            switch (c) {
                case "1" -> {
                    System.out.print("first: ");
                    m.firstName = sc.nextLine();
                    System.out.print("last: ");
                    m.lastName = sc.nextLine();
                    ActivityLogger.log(currentUser, "updated member name " + u);
                }
                case "2" -> {
                    System.out.print("new username: ");
                    String nu = sc.nextLine();
                    if (!Auth.usernameTaken(nu)) {
                        Auth.deleteMember(u);
                        m.username = nu;
                        DataStore.members[DataStore.memberCount] = m;
                        DataStore.memberCount++;
                        ActivityLogger.log(currentUser, "changed member username " + u + "->" + nu);
                        return;
                    }
                }
                case "3" -> {
                    System.out.print("new password: ");
                    m.password = sc.nextLine();
                    ActivityLogger.log(currentUser, "changed member password " + u);
                }
                case "4" -> {
                    System.out.print("plan id: ");
                    String pid = sc.nextLine();
                    m.membership = (pid);
                    System.out.print("start: ");
                    m.startDate = sc.nextLine();
                    System.out.print("end: ");
                    m.endDate = sc.nextLine();
                    ActivityLogger.log(currentUser, "changed member membership " + u);
                }
                case "5" -> {
                    return;
                }
            }
        }
    }

    static void manageTrainers(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("Manage Trainers");
            System.out.println(" ");
            System.out.println("1 Add");
            System.out.println("2 View");
            System.out.println("3 Update Specialty");
            System.out.println("4 Delete");
            System.out.println("5 Back");
            String c = sc.nextLine();
            switch (c) {
                case "1" -> {
                    System.out.print("first: ");
                    String f = sc.nextLine();
                    System.out.print("last: ");
                    String l = sc.nextLine();
                    System.out.print("user: ");
                    String u = sc.nextLine();
                    if (Auth.usernameTaken(u)) continue;
                    System.out.print("pass: ");
                    String p = sc.nextLine();
                    System.out.print("specialty: ");
                    String s = sc.nextLine();
                    Auth.registerTrainer(u, p, f, l, s);
                    ActivityLogger.log(currentUser, "added trainer " + u);
                }
                case "2" -> {
                    System.out.print("ID, name, or username, or ALL: ");
                    String q = sc.nextLine();
                    if (q.equals("ALL")) {
                        for (int i = 0; i < DataStore.trainerCount; i++) {
                            System.out.println(DataStore.trainers[i].username);
                        }
                    } else {
                        Trainer t = findTrainer(q);
                        if (t != null) System.out.println(t.username);
                    }
                }
                case "3" -> {
                    System.out.print("ID, name, or username: ");
                    String u = sc.nextLine();
                    Trainer t = findTrainer(u);
                    if (t != null) {
                        System.out.print("specialty: ");
                        t.specialty = sc.nextLine();
                        ActivityLogger.log(currentUser, "updated trainer specialty " + u);
                    }
                }
                case "4" -> {
                    System.out.print("ID, name, or username: ");
                    String u = sc.nextLine();
                    Trainer t = findTrainer(u);
                    if (t != null) {
                        System.out.print("Are you sure you want to delete this trainer? (Y/N): ");
                        String confirm = sc.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            if (deleteTrainer(u)) {
                                ActivityLogger.log(currentUser, "deleted trainer " + u);
                            }
                        } else {
                            System.out.println("Delete cancelled.");
                        }
                    }
                }

                case "5" -> {
                    return;
                }
            }
        }
    }
//Searches for a trainer by iterating through the database
    static Trainer findTrainer(String key) {
        // Assumption: we treat username as the ID,
        // and also allow search by first or last name.
        for (int i = 0; i < DataStore.trainerCount; i++) {
            Trainer t = DataStore.trainers[i];
            if (t.username.equals(key) ||
                    t.firstName.equals(key) ||
                    t.lastName.equals(key)) {
                return t;
            }
        }
        System.out.println("No Trainer Found.");
        return null;
    }


    static boolean deleteTrainer(String u) {
        for (int i = 0; i < DataStore.trainerCount; i++) {
            if (DataStore.trainers[i].username.equals(u)) {
                DataStore.trainers[i] = DataStore.trainers[DataStore.trainerCount - 1];
                DataStore.trainers[DataStore.trainerCount - 1] = null;
                DataStore.trainerCount--;
                return true;
            }
        }
        return false;
    }
//Trainers can use this too.
    static void manageSessions(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("Manage Sessions");
            System.out.println(" ");
            System.out.println("1 Add");
            System.out.println("2 View");
            System.out.println("3 Update");
            System.out.println("4 Delete");
            System.out.println("5 Back");
            String c = sc.nextLine();
            switch (c) {
                case "1" -> {
                    WorkoutSession s = new WorkoutSession();
                    System.out.print("sessionId: ");
                    s.sessionId = sc.nextLine();
                    System.out.print("sessionName: ");
                    s.sessionName = sc.nextLine();
                    System.out.print("type: ");
                    s.type = sc.nextLine();
                    System.out.print("date: ");
                    s.date = sc.nextLine();
                    System.out.print("time: ");
                    s.time = sc.nextLine();
                    System.out.print("capacity: ");
                    s.capacity = parseIntSafe(sc.nextLine());
                    System.out.print("trainerUsername: ");
                    s.trainerUsername = sc.nextLine();
                    DataStore.sessions[DataStore.sessionCount] = s;
                    DataStore.sessionCount++;
                    ActivityLogger.log(currentUser, "added session " + s.sessionId);
                }
                case "2" -> {
                    System.out.print("SessionID, SessionName, Date, or ALL: ");
                    String q = sc.nextLine();
                    if (q.equals("ALL")) {
                        for (int i = 0; i < DataStore.sessionCount; i++) {
                            System.out.println(DataStore.sessions[i].sessionId);
                        }
                    } else {
                        boolean found = false;
                        for (int i = 0; i < DataStore.sessionCount; i++) {
                            WorkoutSession s = DataStore.sessions[i];
                            if (s.sessionId.equals(q) || s.sessionName.equals(q) || s.date.equals(q)) {
                                System.out.println(s.sessionId);
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("Session not found.");
                        }
                    }
                }

                case "3" -> {
                    System.out.print("sessionId: ");
                    String id = sc.nextLine();
                    WorkoutSession s = findSession(id);
                    if (s != null) {
                        System.out.print("date: ");
                        s.date = sc.nextLine();
                        System.out.print("time: ");
                        s.time = sc.nextLine();
                        System.out.print("trainerUsername: ");
                        s.trainerUsername = sc.nextLine();
                        ActivityLogger.log(currentUser, "updated session " + id);
                    }
                }
                case "4" -> {
                    System.out.print("sessionId: ");
                    String id = sc.nextLine();
                    WorkoutSession s = findSession(id);
                    if (s != null) {
                        System.out.print("Are you sure you want to delete this session? (Y/N): ");
                        String confirm = sc.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            if (deleteSession(id)) {
                                ActivityLogger.log(currentUser, "deleted session " + id);
                            }
                        } else {
                            System.out.println("Delete cancelled.");
                        }
                    } else {
                        System.out.println("Session not found.");
                    }
                }

                case "5" -> {
                    return;
                }
            }
        }
    }

    static WorkoutSession findSession(String id) {
        for (int i = 0; i < DataStore.sessionCount; i++) {
            if (DataStore.sessions[i].sessionId.equals(id)) return DataStore.sessions[i];
        }
        return null;
    }

    static boolean deleteSession(String id) {
        for (int i = 0; i < DataStore.sessionCount; i++) {
            if (DataStore.sessions[i].sessionId.equals(id)) {
                DataStore.sessions[i] = DataStore.sessions[DataStore.sessionCount - 1];
                DataStore.sessions[DataStore.sessionCount - 1] = null;
                DataStore.sessionCount--;
                return true;
            }
        }
        return false;
    }
//Membership plan Manager
    static void managePlans(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("1 Add");
            System.out.println("2 View");
            System.out.println("3 Update");
            System.out.println("4 Delete");
            System.out.println("5 Back");
            String c = sc.nextLine();
            switch (c) {
                case "1" -> {
                    MembershipPlan p = new MembershipPlan();
                    System.out.print("planName: ");
                    p.planName = sc.nextLine();
                    System.out.print("durationMonths: ");
                    p.durationMonths = parseIntSafe(sc.nextLine());
                    System.out.print("price: ");
                    p.price = parseDoubleSafe(sc.nextLine());
                    DataStore.plans[DataStore.planCount] = p;
                    DataStore.planCount++;
                    ActivityLogger.log(currentUser, "added plan " + p.planName);
                }
                case "2" -> {
                    System.out.print("planName or ALL: ");
                    String q = sc.nextLine();
                    if (q.equals("ALL")) {
                        for (int i = 0; i < DataStore.planCount; i++) {
                            System.out.println(DataStore.plans[i].planName);
                        }
                    } else {
                        MembershipPlan p = findPlan(q);
                        if (p != null) {
                            System.out.println(p.planName);
                        } else {
                            System.out.println("Plan not found.");
                        }
                    }
                }

                case "3" -> {
                    System.out.print("planName: ");
                    String name = sc.nextLine();
                    MembershipPlan p = findPlan(name);
                    if (p != null) {
                        System.out.print("durationMonths: ");
                        p.durationMonths = parseIntSafe(sc.nextLine());
                        System.out.print("price: ");
                        p.price = parseDoubleSafe(sc.nextLine());
                        ActivityLogger.log(currentUser, "updated plan " + name);
                    }
                }
                case "4" -> {
                    System.out.print("planName: ");
                    String name = sc.nextLine();
                    MembershipPlan p = findPlan(name);
                    if (p != null) {
                        System.out.print("Are you sure you want to delete this plan? (Y/N): ");
                        String confirm = sc.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            if (deletePlan(name)) {
                                ActivityLogger.log(currentUser, "deleted plan " + name);
                            }
                        } else {
                            System.out.println("Delete cancelled.");
                        }
                    } else {
                        System.out.println("Plan not found.");
                    }
                }

                case "5" -> {
                    return;
                }
            }
        }
    }

    static MembershipPlan findPlan(String name) {
        for (int i = 0; i < DataStore.planCount; i++) {
            if (DataStore.plans[i].planName.equals(name)) return DataStore.plans[i];
        }
        return null;
    }

    static boolean deletePlan(String name) {
        for (int i = 0; i < DataStore.planCount; i++) {
            if (DataStore.plans[i].planName.equals(name)) {
                DataStore.plans[i] = DataStore.plans[DataStore.planCount - 1];
                DataStore.plans[DataStore.planCount - 1] = null;
                DataStore.planCount--;
                return true;
            }
        }
        return false;
    }

    static void memberMenu(Scanner sc, Member member) {
        while (true) {
            System.out.println ("Welcome, " + member.username);
            System.out.println("""
            What would you like to do?\n
            1 Manage Plan\n
            2 Find Sessions\n
            3 Join a Session\n
            4 Sign Out\n
            """);
            String c = sc.nextLine();
            switch (c) {
                case "1" -> memberPlanManager(sc, member);
                case "2" -> sessionFinder (sc, member);
                case "3" -> {
                    System.out.println ("Plese enter the ID of the session you would like to join");
                    String id = sc.nextLine ();
                    for(int i = 0; i < DataStore.sessionCount; i++){
                        if(DataStore.sessions[i].sessionId.equals (id))
                            DataStore.sessions[i].addMember (member.firstName);
                        System.out.println ("Successfully joined session" + DataStore.sessions[i].sessionId);
                    }
                }
                default -> {
                    return;
                }
            }
        }
    }

    public static void sessionFinder(Scanner sc, Member member){
        while (true) {
            System.out.println ("""
                    How would you like to search for a session t join?\
                    1 Name\
                    2 Type\
                    3 Trainer\
                    """);
            String input = sc.nextLine ();
            switch(input){
                case "1" -> {
                    System.out.println ("Please enter the name of the session you would like to find: ");
                    String name = sc.nextLine ();
                    for (int i = 0; i < DataStore.sessionCount; i++){
                        if(DataStore.sessions[i].sessionName.equals (name)){
                            System.out.println(
                                    "Session Found: \n" +
                                    "Name: " + name +
                                    "Session ID: " + DataStore.sessions[i].sessionId +
                                    "Trainer: " + DataStore.sessions[i].trainerUsername +
                                    "Date: " + DataStore.sessions[i].date +
                                    "Time: " + DataStore.sessions[i].time
                                    );
                        }
                    }
                }
                case "2" -> {
                    System.out.println ("Please enter the type of session you would like to find: ");
                    String type = sc.nextLine ();
                    for (int i = 0; i < DataStore.sessionCount; i++){
                        if(DataStore.sessions[i].type.equals (type)){
                            System.out.println(
                                    "Session Found: \n" +
                                            "Name: " + DataStore.sessions[i].sessionName +
                                            "Session ID: " + DataStore.sessions[i].sessionId +
                                            "Trainer: " + DataStore.sessions[i].trainerUsername +
                                            "Date: " + DataStore.sessions[i].date +
                                            "Time: " + DataStore.sessions[i].time
                            );
                        }
                    }
                }
                case "3" -> {
                    System.out.println ("Please enter the name of the trainer you would like to find: ");
                    String name = sc.nextLine ();
                    for (int i = 0; i < DataStore.sessionCount; i++){
                        //this is using a search function in the Session class
                        // assuming the customers will know
                        // the name of the trainers, but not their usernames
                        if(DataStore.sessions[i].findTrainer().equals (name)){
                            System.out.println(
                                    "Session Found: \n" +
                                            "Name: " + DataStore.sessions[i].sessionName +
                                            "Session ID: " + DataStore.sessions[i].sessionId +
                                            "Trainer: " + name +
                                            "Date: " + DataStore.sessions[i].date +
                                            "Time: " + DataStore.sessions[i].time
                            );
                        }
                    }
                }
                default -> memberMenu (sc, member);
            }
        }
    }

    public static void memberPlanManager(Scanner sc, Member member){
        System.out.println ("""
                What would you like to do with your membership plan?\
                
                1 Change Membership Plan\
                
                2 Cancel Membership Plan\
                
                3 return to Member Menu
                """);
        String c = sc.nextLine ();
        switch(c){
            case "1" -> {
                System.out.println ("Membership Plans: ");
                for(int i = 0; i < DataStore.plans.length; i++){
                    System.out.println (i + DataStore.plans[i].planName);
                    System.out.println (i + DataStore.plans[i].price);
                }
                System.out.println ("Which plan would you like to switch to?");
                String choice = sc.nextLine ();
                //Updates membership to corresponding plan
                member.membership = DataStore.plans[Integer.parseInt (choice)].planName;
            }
            case "2" -> {
                System.out.println ("Are you sure you would like to cancel your membership with us? (y/n)");
                String choice = sc.nextLine ();
                if (choice.equals ("y") | choice.equals ("1") | choice.equals ("yes")){
                    //deletes the member
                    member = null;
                    System.out.println ("Membership cancelled.");
                    System.out.println ("Returning to login menu.");
                    login(sc);
                }else{
                    System.out.println ("Returning...");
                    memberPlanManager (sc, member);
                }
            }
            default -> memberMenu (sc, member);
        }
    }
//805-850 Trainer menu
    static void trainerMenu(Scanner sc, Person x) {
        while (true) {
            System.out.println("Logged in as Trainer" +(x.firstName));
            System.out.println(" ");
            System.out.println("1 View Sessions");
            System.out.println("2 View Session Members");
            System.out.println("3 Sign Out");
            String c = sc.nextLine();
            switch (c){
              case "1" -> viewSessions(x);
                case "2" -> {
                    // Placeholder for Part 2
                    System.out.println("Please enter the ID of the Session you wish to view: ");
                    String sessionID = sc.nextLine ();
                    viewSessionMembers(sessionID);
                }
              case "3" -> {
                    return;
                }
            }

        }
    }
//Displays sessions that are linked to the trainer's username
    static void viewSessions(Person p){
        for (int i = 0; i < DataStore.sessionCount; i++) {
            if (DataStore.sessions[i].trainerUsername.equals (p.username)) {
                System.out.println (
                        "Name: " + DataStore.sessions[i].sessionName
                        + ", Date: " +DataStore.sessions[i].date
                        + ", ID: " + DataStore.sessions[i].sessionId);
            }
        }
    }
//Shows members who have joined a session
    static void viewSessionMembers(String session){
        for (int i = 0; i < DataStore.sessionCount; i++) {
            if (DataStore.sessions[i].sessionId.equals(session)) {
                System.out.println (
                        "Name: " + DataStore.sessions[i].sessionName
                                + DataStore.sessions[i].members);
            }
        }
    }

    static int parseIntSafe(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }

    static double parseDoubleSafe(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0.0; }
    }
}