package in.ty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(name= "patient_id_generator",initialValue = 10, allocationSize =1,sequenceName = "patient_id_seq")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "doctor_id_generator")
	private Long id;
	private String houseNumber;
	private Long pincode;
	private String city;
	@OneToOne(mappedBy="address")
	private Patient patient;
}
