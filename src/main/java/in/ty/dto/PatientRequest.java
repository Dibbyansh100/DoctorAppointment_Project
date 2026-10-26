package in.ty.dto;

import in.ty.entity.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record PatientRequest(
		@NotBlank(message = "Patient name should be blank")
		String name,
		@Positive
		int age,
		@NotBlank
		@Size(min = 10, max = 10)
		String phone,
		Address address
		) {

}
