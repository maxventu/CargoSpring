package models;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;

    public Date date;

    @Column(name = "transportation_cost_per_km")
    public Double transportationCostPerKm;

    public Boolean locked;
}
