package com.clinic.appointment.doctor.dto;

import com.clinic.appointment.appointment.Specalization;

public record DoctorResponse(
		Long id,
		String name,
		String email,
		String phone,
		Specalization specilization,
		Double consultationFee,
		Boolean isActive
		) {

}
