package dao.entity;




import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String country;

    private String city;

    private String street;

    private String house;

    private String flat;
}
