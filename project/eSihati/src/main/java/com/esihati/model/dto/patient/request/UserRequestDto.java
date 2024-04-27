package com.esihati.model.dto.patient.request;

import com.esihati.model.Location;
import com.esihati.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private Long id;
    private String email;
    private String phone;
    private String password;

    private Location location;

}
