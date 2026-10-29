package com.clinic.appointment.patient.dto;

import com.clinic.appointment.address.Address;

public record PatientResponse(
		  long id,
		  String name,
		  int age,
		  String phone,
		  Address address,
		  boolean isActive
		) {

}
