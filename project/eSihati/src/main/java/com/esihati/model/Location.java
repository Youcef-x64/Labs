package com.esihati.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private State state;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private City city;
    @OneToOne(cascade = CascadeType.PERSIST)
    private GeoLocation geoLocation;

}
