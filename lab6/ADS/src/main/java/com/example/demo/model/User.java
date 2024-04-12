package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@TableGenerator(
        name = "CustomIdGenerator",
        table = "custom_id_generator",
        pkColumnValue = "user_id",
        allocationSize = 1
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CustomIdGenerator")
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;

}
