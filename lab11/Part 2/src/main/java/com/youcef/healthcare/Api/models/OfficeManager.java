package com.youcef.healthcare.Api.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@DiscriminatorValue("Office_Manager")
public class OfficeManager extends _User{
}
