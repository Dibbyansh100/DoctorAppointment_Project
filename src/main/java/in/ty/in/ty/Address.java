package in.ty.in.ty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {
	@Id
	private long id;
	private String houseNumber;
	private long pincode;
	private String city;
}
