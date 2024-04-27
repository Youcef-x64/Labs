package com.esihati.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WrongCredentialException extends Exception {

    private Long id;

    public WrongCredentialException(Long id, String message) {
        super(message);
        this.id = id;
    }

}
