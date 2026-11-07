package composite;

import model.StudentAccount;

/**
 * Composite pattern: Leaf.
 * Wraps one StudentAccount that already exists in AccountRepository.
 */
public class AccountLeaf implements AcademicNode {

    private final StudentAccount account;

    public AccountLeaf(StudentAccount account) {
        this.account = account;
    }

    @Override
    public String getName() {
        return account.getFullName();
    }

    @Override
    public int countAccounts() {
        return 1; // الورقة تحتوي حساباً واحداً
    }

    @Override
    public void show(String indent) {
        System.out.println(indent + "- " + account.getFullName() + " (" + account.getId() + ")");
    }
}