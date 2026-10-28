package in.ty.dto;

import in.ty.entity.Specalization;
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
