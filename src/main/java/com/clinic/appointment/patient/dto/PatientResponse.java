package com.clinic.appointment.patient.dto;

import java.util.UUID;

import com.clinic.appointment.address.Address;

public record PatientResponse(
		  UUID id,
		  String name,
		  int age,
		  String phone,
		  Address address,
		  boolean isActive
		) {

}
