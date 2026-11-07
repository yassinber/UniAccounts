package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite pattern: Composite.
 * Represents university, faculty, department, or section.
 * Its children can be leaves or other groups.
 */
public class AcademicGroup implements AcademicNode {

    private final String kind;
    private final String name;
    private final List<AcademicNode> children = new ArrayList<>();

    public AcademicGroup(String kind, String name) {
        this.kind = kind;
        this.name = name;
    }

    public void add(AcademicNode child) {
        children.add(child);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int countAccounts() {
        int total = 0;
        for (AcademicNode child : children) {
            total += child.countAccounts(); // استدعاء ذاتي: يعمل على أي عمق
        }
        return total;
    }

    @Override
    public void show(String indent) {
        System.out.println(indent + kind + ": " + name + " (" + countAccounts() + " حساب)");
        for (AcademicNode child : children) {
            child.show(indent + "    ");
        }
    }
}