import audit.AuditLog;
import composite.AccountLeaf;
import composite.AcademicGroup;
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

        StudentAccount a2 = batch.cloneWithIdentity("20260002", "Nadia Ouali", "nadia@univ.dz");
        StudentAccount a3 = batch.cloneWithIdentity("20260003", "Rida Benali", "rida@univ.dz");
        repo.save(a2);
        repo.save(a3);

        // 3. Duplicate id: must be rejected
        try {
            repo.save(batch.cloneWithIdentity("20260002", "Other Person", "other@univ.dz"));
        } catch (IllegalStateException e) {
            System.out.println("Error caught: " + e.getMessage());
        }

        // 4. Singleton check
        System.out.println("Same repository instance? " + (repo == AccountRepository.getInstance()));
        System.out.println("Same audit log instance? " + (AuditLog.getInstance() == AuditLog.getInstance()));

        // 5. Stored accounts
        System.out.println("\nAccounts stored: " + repo.count());
        for (StudentAccount a : repo.findAll()) {
            System.out.println(a);
        }

        // 6. Composite: academic tree built from accounts already in the repository
        AcademicGroup university = new AcademicGroup("جامعة", "UniAccounts");
        AcademicGroup faculty = new AcademicGroup("كلية", "Sciences exactes");
        AcademicGroup department = new AcademicGroup("قسم", "Informatique");
        AcademicGroup section1 = new AcademicGroup("فوج", "Fauj 1");
        AcademicGroup section2 = new AcademicGroup("فوج", "Fauj 2");

        section1.add(new AccountLeaf(a1));
        section1.add(new AccountLeaf(a2));
        section2.add(new AccountLeaf(a3));

        department.add(section1);
        department.add(section2);
        faculty.add(department);
        university.add(faculty);

        System.out.println("\n--- Academic tree ---");
        university.show("");
        System.out.println("Total accounts under university: " + university.countAccounts());
        System.out.println("Accounts under section 2 only: " + section2.countAccounts());

        // 7. Audit log
        System.out.println("\n--- Audit log ---");
        for (String entry : AuditLog.getInstance().getEntries()) {
            System.out.println(entry);
        }
    }
}