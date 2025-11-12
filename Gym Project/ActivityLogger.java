import java.io.FileWriter;

public class ActivityLogger {
    public static void log(String user, String action) {
        try {
            FileWriter w = new FileWriter("activity.log", true);
            w.write(user + " " + action + "\n");
            w.close();
        } catch (Exception e) {}
    }
}
