package com.clinic.appointment.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseStructure<T> {

    private int statusCode;
    private String message;
    private T data;
}
