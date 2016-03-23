package models;


import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String country;

    public String city;

    public String street;

    public String house;

    public String flat;
}
