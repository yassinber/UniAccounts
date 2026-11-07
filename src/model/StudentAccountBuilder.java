package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder pattern.
 * Solves the problem of a constructor with 8 parameters, some optional.
 * Required fields go in the constructor, optional ones through named methods.
 * build() validates everything before creating the account.
 */
public class StudentAccountBuilder {

    // Required
    private final String id;
    private final String fullName;
    private final String email;

    // Optional, with defaults
    private String phone = null;
    private String level = "L1";
    private String major = null;
    private boolean scholarship = false;
    private final List<String> courses = new ArrayList<>();

    public StudentAccountBuilder(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public StudentAccountBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public StudentAccountBuilder level(String level) {
        if (level == null || !StudentAccount.VALID_LEVELS.contains(level)) {
            throw new IllegalArgumentException("Invalid level: " + level
                    + ". Allowed: " + StudentAccount.VALID_LEVELS);
        }
        this.level = level;
        return this;
    }

    public StudentAccountBuilder major(String major) {
        this.major = major;
        return this;
    }

    public StudentAccountBuilder scholarship(boolean scholarship) {
        this.scholarship = scholarship;
        return this;
    }

    public StudentAccountBuilder addCourse(String course) {
        if (course != null && !course.isBlank()) {
            this.courses.add(course);
        }
        return this;
    }

    public StudentAccount build() {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id is required and cannot be empty");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("fullName is required");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email is required and must contain '@'");
        }
        return new StudentAccount(id, fullName, email, phone, level, major, scholarship, courses);
    }
}
