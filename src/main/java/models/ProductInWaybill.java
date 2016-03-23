package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "product_in_wvd")
public class ProductInWaybill implements Serializable{

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            updatable = false)
    public Product product;

    @Id
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wvd_id",
            referencedColumnName = "id")
    public WaybillVehicleDriver waybillVehicleDriver;

    @Column(name = "quantity")
    public Long quantity;

    @Override
    public int hashCode() {
        int result = 31 * (product != null ? product.hashCode() : 0);
        result = 31 * result + (waybillVehicleDriver != null ? waybillVehicleDriver.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ProductInWaybill that = (ProductInWaybill) obj;

        return (product != null ? product.equals(that.product) : that.product == null)
                && (waybillVehicleDriver != null ? waybillVehicleDriver.equals(that.waybillVehicleDriver) : that.waybillVehicleDriver == null)
                && (quantity != null ? quantity.equals(that.quantity) : that.quantity == null);
    }

    public ProductInWaybill(){}

    public ProductInWaybill(Product product, Long quantity){
        this.product = product;
        this.quantity = quantity;
    }
}