// User.java
package CampusCommunicator.models;

public class User {
    private String id;
    private String name;
    private String email;
    private String role; // admin, faculty, student
    private String subject; // Java, Python, etc.

    public User(String id, String name, String email, String role, String subject) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.subject = subject;
    }
    private String password;

    public User(String id, String name, String email, String role, String subject, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.role = role;
    this.subject = subject;
    this.password = password;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + email + "," + role + "," + subject;
    }

    public static User fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
    public String toCSV() {
        return id + "," + name + "," + email + "," + role + "," + subject;
    }        
}
