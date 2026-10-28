package in.ty.dto;

import in.ty.entity.Specalization;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DoctorRequest(
		@NotBlank(message = "Doctor name should be blank")
		String name,
		@NotBlank
		String email,
		@Size(min = 10, max = 10)
		String phone,
		Specalization specilization,
		Double consultationFee,
		Boolean active
		) {

}
