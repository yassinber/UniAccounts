package repository;
import audit.AuditLog;
import model.StudentAccount;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Singleton pattern.
 * The central store for all accounts. There is only one repository,
 * so accounts are never split between separate copies.
 * This class also enforces that each id is unique, as the assignment requires.
 */
public class AccountRepository {

    // Singleton: the only instance
    private static final AccountRepository INSTANCE = new AccountRepository();

    private final Map<String, StudentAccount> accounts = new LinkedHashMap<>();

    private AccountRepository() {
    }

    public static AccountRepository getInstance() {
        return INSTANCE;
    }

    public synchronized void save(StudentAccount account) {
        if (accounts.containsKey(account.getId())) {
            throw new IllegalStateException("Duplicate id: " + account.getId());
        }
        accounts.put(account.getId(), account);
        AuditLog.getInstance().log("CREATED account " + account.getId());
    }

    public synchronized Optional<StudentAccount> findById(String id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized List<StudentAccount> findAll() {
        return List.copyOf(accounts.values());
    }

    public synchronized int count() {
        return accounts.size();
    }
}