package in.ty.dto;

import in.ty.entity.Specalization;

public record DoctorResponse(
		Long id,
		String name,
		String email,
		String phone,
		Specalization specilization,
		Double consultationFee,
		Boolean active
		) {

}
