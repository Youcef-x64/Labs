package com.esihati.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DoctorStatic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long viewNumber;
    private Long visitorNumber;
    private Float rate;
    private Integer rateStarOne;
    private Integer rateStarTwo;
    private Integer rateStarThree;
    private Integer rateStarFour;
    private Integer rateStarFive;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Doctor doctor;

}
