package in.ty.dto;

import in.ty.entity.Address;

public record PatientResponse(
		  long id,
		  String name,
		  int age,
		  String phone,
		  Address address,
		  boolean isActive
		) {

}
