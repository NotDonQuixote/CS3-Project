import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ActivityLogger {
    public static void log(String username, String action) {
        try (FileWriter writer = new FileWriter("ActivityLog.txt", true)) {
            String time = LocalDateTime.now().toString();
            writer.write(time + " - " + username + " " + action + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Could not write to log file.");
        }
    }
}
