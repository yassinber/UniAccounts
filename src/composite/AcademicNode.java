package composite;

/**
 * Composite pattern: Component.
 * The common interface for every node in the academic tree,
 * whether it is a single account (leaf) or a group (composite).
 */
public interface AcademicNode {
    String getName();
    int countAccounts();
    void show(String indent);
}