package audit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton pattern.
 * There must be exactly one audit log in the whole program,
 * so every operation is recorded in the same place.
 */
public class AuditLog {

    // Singleton: the only instance, created once when the class is loaded
    private static final AuditLog INSTANCE = new AuditLog();

    private final List<String> entries = new ArrayList<>();

    // Private constructor: nobody outside this class can call new AuditLog()
    private AuditLog() {
    }

    public static AuditLog getInstance() {
        return INSTANCE;
    }

    public synchronized void log(String message) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        entries.add(time + " | " + message);
    }

    public synchronized List<String> getEntries() {
        return Collections.unmodifiableList(new ArrayList<>(entries));
    }
}