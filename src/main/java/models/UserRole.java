package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class UserRole  {
    @Id
    public Long id;

    public String name;

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
