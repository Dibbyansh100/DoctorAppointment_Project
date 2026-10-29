package com.clinic.appointment.patient.dto;

import com.clinic.appointment.address.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PatientRequest(
		@NotBlank(message = "Patient name should be blank")
		String name,
		@Positive
		int age,
		@NotBlank
		@Size(min = 10, max = 10)
		String phone,
		Address address
		) {

}
