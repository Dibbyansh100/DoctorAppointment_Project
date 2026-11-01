package com.clinic.appointment.doctor.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.clinic.appointment.appointment.Specalization;

public record DoctorResponse(
		UUID id,
		String name,
		String email,
		String phone,
		Specalization specilization,
		BigDecimal consultationFee,
		Boolean isActive
		) {

}
