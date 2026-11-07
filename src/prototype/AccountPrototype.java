package prototype;

import model.StudentAccount;
import model.StudentAccountBuilder;

/**
 * Prototype pattern.
 * Holds a ready template for a batch (same major, level, and courses).
 * cloneWithIdentity() copies the template and changes only the student's identity.
 *
 * Design choice: the copy is made through the public Builder and public getters,
 * so the model package does not need to be changed.
 */
public class AccountPrototype {

    private final StudentAccount template;

    public AccountPrototype(StudentAccount template) {
        this.template = template;
    }

    public StudentAccount cloneWithIdentity(String id, String fullName, String email) {
        StudentAccountBuilder builder = new StudentAccountBuilder(id, fullName, email)
                .phone(template.getPhone())
                .level(template.getLevel())
                .major(template.getMajor())
                .scholarship(template.isScholarship());

        for (String course : template.getCourses()) {
            builder.addCourse(course);
        }
        return builder.build();
    }
}