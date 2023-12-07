package com.example.user;

import com.example.pet.Pet;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 20, nullable = false)
    private String password;
    @Column(length = 50, nullable = false, name="first_name")
    private String firstName;
    @Column(length = 50, nullable = false, name="last_name")
    private String lastName;

    private boolean enabled;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)

    //@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST})
    private List<Pet> pets = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        pets.forEach( pet -> pet.setUser(null));
    }

    public List<Pet> getPets(){ return pets;}
    public void setPets(List<Pet> pets) { this.pets = pets;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
