package in.ty.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(name= "patient_id_generator",initialValue = 10, allocationSize =1,sequenceName = "patient_id_seq")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_id_generator")
	private long id;
	@Column(name = "p_name", nullable = false)
	private String name;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false, unique = true, length = 10)
	private String phone;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@Column(name = "is_active")
	private boolean isActive = true;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="patient")
	private List<Appointment> appointment;

}
