import java.util.Scanner;

public class RunGym {
    public static void main(String[] args) {
        CsvIO.loadUsers("GymUsersData.csv");
        CsvIO.loadSessions("GymSessions.csv");
        CsvIO.loadPlans("GymPlans.csv");

        if (DataStore.adminCount == 0) {
            Administrator a = new Administrator();
            a.username = "admin";
            a.password = "admin";
            a.firstName = "Admin";
            a.lastName = "User";
            DataStore.admins[0] = a;
            DataStore.adminCount = 1;
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1 Register");
            System.out.println("2 Login");
            System.out.println("3 Exit");
            String c = sc.nextLine();
            if (c.equals("1")) register(sc);
            else if (c.equals("2")) login(sc);
            else if (c.equals("3")) {
                CsvIO.saveUsers("GymUsersData.csv");
                CsvIO.saveSessions("GymSessions.csv");
                CsvIO.savePlans("GymPlans.csv");
                return;
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
        if (Auth.usernameTaken(u)) return;
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
        if (x instanceof Administrator) {
            ActivityLogger.log(u, "logged in");
            adminMenu(sc, u);
        } else if (x instanceof Member) {
            ActivityLogger.log(u, "logged in");
            memberMenu(sc);
        } else if (x instanceof Trainer) {
            ActivityLogger.log(u, "logged in");
            trainerMenu(sc);
        }
    }

    static void adminMenu(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("1 Manage Members");
            System.out.println("2 Manage Trainers");
            System.out.println("3 Manage Workout Sessions");
            System.out.println("4 Manage Membership Plans");
            System.out.println("5 Sign Out");
            String c = sc.nextLine();
            if (c.equals("1")) manageMembers(sc, currentUser);
            else if (c.equals("2")) manageTrainers(sc, currentUser);
            else if (c.equals("3")) manageSessions(sc, currentUser);
            else if (c.equals("4")) managePlans(sc, currentUser);
            else if (c.equals("5")) return;
        }
    }

    static void manageMembers(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("1 Add");
            System.out.println("2 View");
            System.out.println("3 Update");
            System.out.println("4 Delete");
            System.out.println("5 Back");
            String c = sc.nextLine();
            if (c.equals("1")) {
                System.out.print("first: "); String f = sc.nextLine();
                System.out.print("last: "); String l = sc.nextLine();
                System.out.print("user: "); String u = sc.nextLine();
                if (Auth.usernameTaken(u)) continue;
                System.out.print("pass: "); String p = sc.nextLine();
                Auth.registerMember(u, p, f, l);
                ActivityLogger.log(currentUser, "added member " + u);
            } else if (c.equals("2")) {
                System.out.print("user or ALL: ");
                String q = sc.nextLine();
                if (q.equals("ALL")) {
                    for (int i = 0; i < DataStore.memberCount; i++) {
                        System.out.println(DataStore.members[i].username);
                    }
                } else {
                    Member m = Auth.findMember(q);
                    if (m != null) System.out.println(m.username);
                }
            } else if (c.equals("3")) {
                updateMember(sc, currentUser);
            } else if (c.equals("4")) {
                System.out.print("user: "); String u = sc.nextLine();
                if (Auth.deleteMember(u)) ActivityLogger.log(currentUser, "deleted member " + u);
            } else if (c.equals("5")) return;
        }
    }

    static void updateMember(Scanner sc, String currentUser) {
        System.out.print("user: "); String u = sc.nextLine();
        Member m = Auth.findMember(u);
        if (m == null) return;
        while (true) {
            System.out.println("1 Change Name");
            System.out.println("2 Change Username");
            System.out.println("3 Change Password");
            System.out.println("4 Change Membership");
            System.out.println("5 Back");
            String c = sc.nextLine();
            if (c.equals("1")) {
                System.out.print("first: "); m.firstName = sc.nextLine();
                System.out.print("last: "); m.lastName = sc.nextLine();
                ActivityLogger.log(currentUser, "updated member name " + u);
            } else if (c.equals("2")) {
                System.out.print("new username: "); String nu = sc.nextLine();
                if (!Auth.usernameTaken(nu)) {
                    Auth.deleteMember(u);
                    m.username = nu;
                    DataStore.members[DataStore.memberCount] = m;
                    DataStore.memberCount++;
                    ActivityLogger.log(currentUser, "changed member username " + u + "->" + nu);
                    return;
                }
            } else if (c.equals("3")) {
                System.out.print("new password: "); m.password = sc.nextLine();
                ActivityLogger.log(currentUser, "changed member password " + u);
            } else if (c.equals("4")) {
                System.out.print("plan id: "); String pid = sc.nextLine();
                m.membership = parseIntSafe(pid);
                System.out.print("start: "); m.startDate = sc.nextLine();
                System.out.print("end: "); m.endDate = sc.nextLine();
                ActivityLogger.log(currentUser, "changed member membership " + u);
            } else if (c.equals("5")) return;
        }
    }

    static void manageTrainers(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("1 Add");
            System.out.println("2 View");
            System.out.println("3 Update Specialty");
            System.out.println("4 Delete");
            System.out.println("5 Back");
            String c = sc.nextLine();
            if (c.equals("1")) {
                System.out.print("first: "); String f = sc.nextLine();
                System.out.print("last: "); String l = sc.nextLine();
                System.out.print("user: "); String u = sc.nextLine();
                if (Auth.usernameTaken(u)) continue;
                System.out.print("pass: "); String p = sc.nextLine();
                System.out.print("specialty: "); String s = sc.nextLine();
                Auth.registerTrainer(u, p, f, l, s);
                ActivityLogger.log(currentUser, "added trainer " + u);
            } else if (c.equals("2")) {
                System.out.print("user or ALL: ");
                String q = sc.nextLine();
                if (q.equals("ALL")) {
                    for (int i = 0; i < DataStore.trainerCount; i++) {
                        System.out.println(DataStore.trainers[i].username);
                    }
                } else {
                    Trainer t = findTrainer(q);
                    if (t != null) System.out.println(t.username);
                }
            } else if (c.equals("3")) {
                System.out.print("user: "); String u = sc.nextLine();
                Trainer t = findTrainer(u);
                if (t != null) {
                    System.out.print("specialty: "); t.specialty = sc.nextLine();
                    ActivityLogger.log(currentUser, "updated trainer specialty " + u);
                }
            } else if (c.equals("4")) {
                System.out.print("user: "); String u = sc.nextLine();
                if (deleteTrainer(u)) ActivityLogger.log(currentUser, "deleted trainer " + u);
            } else if (c.equals("5")) return;
        }
    }

    static Trainer findTrainer(String u) {
        for (int i = 0; i < DataStore.trainerCount; i++) {
            if (DataStore.trainers[i].username.equals(u)) return DataStore.trainers[i];
        }
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

    static void manageSessions(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("1 Add");
            System.out.println("2 View");
            System.out.println("3 Update");
            System.out.println("4 Delete");
            System.out.println("5 Back");
            String c = sc.nextLine();
            if (c.equals("1")) {
                WorkoutSession s = new WorkoutSession();
                System.out.print("sessionId: "); s.sessionId = sc.nextLine();
                System.out.print("sessionName: "); s.sessionName = sc.nextLine();
                System.out.print("type: "); s.type = sc.nextLine();
                System.out.print("date: "); s.date = sc.nextLine();
                System.out.print("time: "); s.time = sc.nextLine();
                System.out.print("capacity: "); s.capacity = parseIntSafe(sc.nextLine());
                System.out.print("trainerUsername: "); s.trainerUsername = sc.nextLine();
                DataStore.sessions[DataStore.sessionCount] = s;
                DataStore.sessionCount++;
                ActivityLogger.log(currentUser, "added session " + s.sessionId);
            } else if (c.equals("2")) {
                System.out.print("id or ALL: ");
                String q = sc.nextLine();
                if (q.equals("ALL")) {
                    for (int i = 0; i < DataStore.sessionCount; i++) {
                        System.out.println(DataStore.sessions[i].sessionId);
                    }
                } else {
                    WorkoutSession s = findSession(q);
                    if (s != null) System.out.println(s.sessionId);
                }
            } else if (c.equals("3")) {
                System.out.print("sessionId: "); String id = sc.nextLine();
                WorkoutSession s = findSession(id);
                if (s != null) {
                    System.out.print("date: "); s.date = sc.nextLine();
                    System.out.print("time: "); s.time = sc.nextLine();
                    System.out.print("trainerUsername: "); s.trainerUsername = sc.nextLine();
                    ActivityLogger.log(currentUser, "updated session " + id);
                }
            } else if (c.equals("4")) {
                System.out.print("sessionId: "); String id = sc.nextLine();
                if (deleteSession(id)) ActivityLogger.log(currentUser, "deleted session " + id);
            } else if (c.equals("5")) return;
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

    static void managePlans(Scanner sc, String currentUser) {
        while (true) {
            System.out.println("1 Add");
            System.out.println("2 View");
            System.out.println("3 Update");
            System.out.println("4 Delete");
            System.out.println("5 Back");
            String c = sc.nextLine();
            if (c.equals("1")) {
                MembershipPlan p = new MembershipPlan();
                System.out.print("planName: "); p.planName = sc.nextLine();
                System.out.print("durationMonths: "); p.durationMonths = parseIntSafe(sc.nextLine());
                System.out.print("price: "); p.price = parseDoubleSafe(sc.nextLine());
                DataStore.plans[DataStore.planCount] = p;
                DataStore.planCount++;
                ActivityLogger.log(currentUser, "added plan " + p.planName);
            } else if (c.equals("2")) {
                System.out.print("planName or ALL: ");
                String q = sc.nextLine();
                if (q.equals("ALL")) {
                    for (int i = 0; i < DataStore.planCount; i++) {
                        System.out.println(DataStore.plans[i].planName);
                    }
                } else {
                    MembershipPlan p = findPlan(q);
                    if (p != null) System.out.println(p.planName);
                }
            } else if (c.equals("3")) {
                System.out.print("planName: "); String name = sc.nextLine();
                MembershipPlan p = findPlan(name);
                if (p != null) {
                    System.out.print("durationMonths: "); p.durationMonths = parseIntSafe(sc.nextLine());
                    System.out.print("price: "); p.price = parseDoubleSafe(sc.nextLine());
                    ActivityLogger.log(currentUser, "updated plan " + name);
                }
            } else if (c.equals("4")) {
                System.out.print("planName: "); String name = sc.nextLine();
                if (deletePlan(name)) ActivityLogger.log(currentUser, "deleted plan " + name);
            } else if (c.equals("5")) return;
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

    static void memberMenu(Scanner sc) {
        while (true) {
            System.out.println("1 Manage Plan");
            System.out.println("2 Enroll in a Session");
            System.out.println("3 Sign Out");
            String c = sc.nextLine();
            if (c.equals("3")) return;
        }
    }

    static void trainerMenu(Scanner sc) {
        while (true) {
            System.out.println("1 View Sessions");
            System.out.println("2 View Members");
            System.out.println("3 Sign Out");
            String c = sc.nextLine();
            if (c.equals("3")) return;
        }
    }

    static int parseIntSafe(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }

    static double parseDoubleSafe(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0.0; }
    }
}
