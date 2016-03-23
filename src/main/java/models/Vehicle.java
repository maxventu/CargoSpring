package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@Table(name = "vehicle")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "vehicle_producer")
    public String vehicleProducer;

    @Column(name = "vehicle_model")
    public String vehicleModel;

    @Column(name = "products_constraint")
    public Double productsConstraintValue;

    @Column(name = "license_plate")
    public String licensePlate;

    @Column(name = "fuel_consumption")
    public Double fuelConsumption;

    @ManyToOne
    @JoinColumn(name = "vehicle_fuel_id")
    public VehicleFuel vehicleFuel;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    public VehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company company;

    @Transient
    public User driver;

    public Boolean deleted;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vehicle that = (Vehicle) obj;

        return (id != null ? id.equals(that.id) : that.id == null)
                && (vehicleProducer != null ? vehicleProducer.equals(that.vehicleProducer) : that.vehicleProducer == null)
                && (vehicleModel != null ? vehicleModel.equals(that.vehicleModel) : that.vehicleModel == null)
                && (productsConstraintValue != null ? productsConstraintValue.equals(that.productsConstraintValue) : that.productsConstraintValue == null)
                && (licensePlate != null ? licensePlate.equals(that.licensePlate) : that.licensePlate == null)
                && (fuelConsumption != null ? fuelConsumption.equals(that.fuelConsumption) : that.fuelConsumption == null)
                && (vehicleFuel != null ? vehicleFuel.equals(that.vehicleFuel) : that.vehicleFuel == null)
                && (vehicleType != null ? vehicleType.equals(that.vehicleType) : that.vehicleType == null)
                && (company != null ? company.equals(that.company) : that.company == null)
                && (deleted != null ? deleted.equals(that.deleted) : that.deleted == null);
    }
}
