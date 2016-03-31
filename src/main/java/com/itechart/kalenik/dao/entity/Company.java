package com.itechart.kalenik.dao.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity

@Getter
@Setter
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Date date;

    @Column(name = "transportation_cost_per_km")
    private Double transportationCostPerKm;

    private Boolean locked;
}
