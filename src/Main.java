import audit.AuditLog;
import model.StudentAccount;
import model.StudentAccountBuilder;
import prototype.AccountPrototype;
import repository.AccountRepository;

public class Main {
    public static void main(String[] args) {
        AccountRepository repo = AccountRepository.getInstance();

        // 1. Builder: one account with all optional fields
        StudentAccount a1 = new StudentAccountBuilder("20260001", "Yassin Ali", "yassin@univ.dz")
                .level("M1")
                .major("Informatique")
                .scholarship(true)
                .addCourse("Bases de données")
                .addCourse("Réseaux")
                .build();
        repo.save(a1);

        // 2. Prototype: a template for the batch, then two copies
        StudentAccount template = new StudentAccountBuilder("TEMPLATE", "-", "template@univ.dz")
                .level("M1")
                .major("Informatique")
                .addCourse("Bases de données")
                .addCourse("Réseaux")
                .addCourse("Algorithmes")
                .build();
        AccountPrototype batch = new AccountPrototype(template);

        repo.save(batch.cloneWithIdentity("20260002", "Nadia Ouali", "nadia@univ.dz"));
        repo.save(batch.cloneWithIdentity("20260003", "Rida Benali", "rida@univ.dz"));

        // 3. Duplicate id: must be rejected
        try {
            repo.save(batch.cloneWithIdentity("20260002", "Other Person", "other@univ.dz"));
        } catch (IllegalStateException e) {
            System.out.println("Error caught: " + e.getMessage());
        }

        // 4. Singleton check: both calls return the same object
        System.out.println("Same repository instance? "
                + (repo == AccountRepository.getInstance()));
        System.out.println("Same audit log instance? "
                + (AuditLog.getInstance() == AuditLog.getInstance()));

        // 5. Show the stored accounts
        System.out.println("\nAccounts stored: " + repo.count());
        for (StudentAccount a : repo.findAll()) {
            System.out.println(a);
        }

        // 6. Show the audit log
        System.out.println("\n--- Audit log ---");
        for (String entry : AuditLog.getInstance().getEntries()) {
            System.out.println(entry);
        }
    }
}