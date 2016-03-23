package models;

import javax.persistence.*;

@Entity
@Table(name = "vehicle_fuel")
public class VehicleFuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    public String fuelName;

    @Column(name = "cost")
    public Double fuelCost;
}
