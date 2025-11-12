import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class CsvIO {
    public static void loadUsers(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);
            if (sc.hasNextLine()) sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] x = line.split(",", -1);
                if (x.length == 0) continue;
                String role = x[0];
                if (role.equalsIgnoreCase("ADMIN")) {
                    Administrator a = new Administrator();
                    a.username = val(x,1);
                    a.password = val(x,2);
                    a.firstName = val(x,3);
                    a.lastName = val(x,4);
                    DataStore.admins[DataStore.adminCount++] = a;
                } else if (role.equalsIgnoreCase("MEMBER")) {
                    Member m = new Member();
                    m.username = val(x,1);
                    m.password = val(x,2);
                    m.firstName = val(x,3);
                    m.lastName = val(x,4);
                    m.startDate = val(x,5);
                    m.endDate = val(x,6);
                    m.membership = parseIntSafe(val(x,7));
                    DataStore.members[DataStore.memberCount++] = m;
                } else if (role.equalsIgnoreCase("TRAINER")) {
                    Trainer t = new Trainer();
                    t.username = val(x,1);
                    t.password = val(x,2);
                    t.firstName = val(x,3);
                    t.lastName = val(x,4);
                    t.specialty = val(x,5);
                    DataStore.trainers[DataStore.trainerCount++] = t;
                }
            }
            sc.close();
        } catch (Exception e) {}
    }

    public static void saveUsers(String path) {
        try {
            FileWriter w = new FileWriter(path);
            w.write("role,username,password,firstName,lastName,startDate,endDate,membership,specialty\n");
            for (int i = 0; i < DataStore.adminCount; i++) {
                Administrator a = DataStore.admins[i];
                w.write("ADMIN,"+s(a.username)+","+s(a.password)+","+s(a.firstName)+","+s(a.lastName)+",,,,\n");
            }
            for (int i = 0; i < DataStore.memberCount; i++) {
                Member m = DataStore.members[i];
                w.write("MEMBER,"+s(m.username)+","+s(m.password)+","+s(m.firstName)+","+s(m.lastName)+","+s(m.startDate)+","+s(m.endDate)+","+m.membership+",\n");
            }
            for (int i = 0; i < DataStore.trainerCount; i++) {
                Trainer t = DataStore.trainers[i];
                w.write("TRAINER,"+s(t.username)+","+s(t.password)+","+s(t.firstName)+","+s(t.lastName)+",,,"+","+s(t.specialty)+"\n");
            }
            w.close();
        } catch (Exception e) {}
    }

    public static void loadSessions(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);
            if (sc.hasNextLine()) sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] x = line.split(",", -1);
                WorkoutSession s = new WorkoutSession();
                s.sessionId = val(x,0);
                s.sessionName = val(x,1);
                s.type = val(x,2);
                s.date = val(x,3);
                s.time = val(x,4);
                s.capacity = parseIntSafe(val(x,5));
                s.trainerUsername = val(x,6);
                DataStore.sessions[DataStore.sessionCount++] = s;
            }
            sc.close();
        } catch (Exception e) {}
    }

    public static void saveSessions(String path) {
        try {
            FileWriter w = new FileWriter(path);
            w.write("sessionId,sessionName,type,date,time,capacity,trainerUsername\n");
            for (int i = 0; i < DataStore.sessionCount; i++) {
                WorkoutSession s = DataStore.sessions[i];
                w.write(s(s.sessionId)+","+s(s.sessionName)+","+s(s.type)+","+s(s.date)+","+s(s.time)+","+s.capacity+","+s(s.trainerUsername)+"\n");
            }
            w.close();
        } catch (Exception e) {}
    }

    public static void loadPlans(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) return;
            Scanner sc = new Scanner(f);
            if (sc.hasNextLine()) sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] x = line.split(",", -1);
                MembershipPlan p = new MembershipPlan();
                p.planName = val(x,0);
                p.durationMonths = parseIntSafe(val(x,1));
                p.price = parseDoubleSafe(val(x,2));
                DataStore.plans[DataStore.planCount++] = p;
            }
            sc.close();
        } catch (Exception e) {}
    }

    public static void savePlans(String path) {
        try {
            FileWriter w = new FileWriter(path);
            w.write("planName,durationMonths,price\n");
            for (int i = 0; i < DataStore.planCount; i++) {
                MembershipPlan p = DataStore.plans[i];
                w.write(s(p.planName)+","+p.durationMonths+","+p.price+"\n");
            }
            w.close();
        } catch (Exception e) {}
    }

    static String val(String[] x, int i) {
        if (i < 0 || i >= x.length) return "";
        return x[i];
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
