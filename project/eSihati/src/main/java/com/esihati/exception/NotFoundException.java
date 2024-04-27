package com.esihati.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends Exception {

    private Long id;

    public NotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }

}
