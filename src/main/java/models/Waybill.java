package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import models.statuses.WaybillStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "waybill")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Waybill {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "departure_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date departureDate;

    @Column(name = "arrival_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date arrivalDate;

    @OneToOne
    @JoinColumn(name = "packing_list_id", nullable = false)
    public PackingList packingList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", nullable = false)
    public User manager;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public WaybillStatus status;

    @OneToMany(mappedBy = "waybill", fetch = FetchType.LAZY)
    public Set<WaybillVehicleDriver> vehicleDrivers;

    @OneToMany(mappedBy = "waybill", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Waypoint> waypoints;
}
