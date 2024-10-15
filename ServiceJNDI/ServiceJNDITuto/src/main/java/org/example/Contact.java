package org.example;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import java.io.Serializable;

public class Contact implements Referenceable, Serializable {
    private String id;
    private String name;
    private String email;
    private String department;

    public Contact(String id, String name, String email, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    // Getters and toString method
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    // Implementing getReference method from Referenceable interface
    @Override
    public Reference getReference() throws NamingException {
        return new Reference(
                Contact.class.getName(),
                "org.example.ContactFactory",
                null // Provide the factory location if needed
        );
    }
}