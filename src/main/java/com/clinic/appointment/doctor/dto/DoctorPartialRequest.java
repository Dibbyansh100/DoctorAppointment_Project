package com.clinic.appointment.doctor.dto;

import com.clinic.appointment.appointment.Specalization;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DoctorPartialRequest(
		@NotBlank
		@Size(min=2)
		String name,
		@NotBlank
		Specalization specilization
		) {

}
