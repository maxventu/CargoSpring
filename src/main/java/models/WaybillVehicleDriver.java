package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import models.statuses.WaybillStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "waybill_vehicle_driver")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WaybillVehicleDriver implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waybill_id",
            referencedColumnName = "id")
    public Waybill waybill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id",
            referencedColumnName = "id")
    public Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id",
            referencedColumnName = "id")
    public User driver;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public WaybillStatus status;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public List<ProductInWaybill> productsInWaybill;

    @Override
    public int hashCode() {
        int result = 31 * (waybill != null ? waybill.hashCode() : 0);
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        WaybillVehicleDriver that = (WaybillVehicleDriver) obj;

        return (waybill != null ? waybill.equals(that.waybill) : that.waybill == null)
                && (vehicle != null ? vehicle.equals(that.vehicle) : that.vehicle == null)
                && (driver != null ? driver.equals(that.driver) : that.driver == null);
    }
}
