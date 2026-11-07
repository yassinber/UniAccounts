package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Product class of the Builder pattern.
 * Holds the student's data. Created only through StudentAccountBuilder.
 */
public class StudentAccount {

    public static final List<String> VALID_LEVELS = List.of("L1", "L2", "L3", "M1", "M2");

    private final String id;           // required, unique in the repository
    private final String fullName;     // required
    private final String email;        // required, must contain '@'
    private final String phone;        // optional, default null
    private final String level;        // optional, default "L1"
    private final String major;        // optional
    private final boolean scholarship; // optional, default false
    private final List<String> courses;// optional, default empty

    // Package-private: only the Builder in this package can call it
    StudentAccount(String id, String fullName, String email, String phone,
                   String level, String major, boolean scholarship, List<String> courses) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.level = level;
        this.major = major;
        this.scholarship = scholarship;
        this.courses = new ArrayList<>(courses);
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLevel() { return level; }
    public String getMajor() { return major; }
    public boolean isScholarship() { return scholarship; }

    // Returns a read-only view so callers cannot change the courses from outside
    public List<String> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    @Override
    public String toString() {
        return "StudentAccount{id=" + id + ", fullName=" + fullName + ", email=" + email
                + ", level=" + level + ", major=" + major
                + ", scholarship=" + scholarship + ", courses=" + courses + "}";
    }
}