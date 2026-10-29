package com.clinic.appointment.doctor.dto;

import com.clinic.appointment.appointment.Specalization;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record DoctorRequest(
		@NotBlank(message = "Doctor name should be blank")
		String name,
		@NotBlank
		String email,
		@NotBlank
		@Size(min = 10, max = 10)
		String phone,
		@NotBlank
		Specalization specilization,
		@PositiveOrZero
		Double consultationFee
		) {

}
