package com.clinic.appointment.patient.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PatientPartialRequest(
		@NotNull
		@Size(min=2)
		String name,
		@Positive
        int age,
        @Size(min = 10, max = 10)
       String phone) {
	 

}
