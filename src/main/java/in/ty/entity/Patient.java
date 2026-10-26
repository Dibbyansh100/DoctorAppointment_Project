package in.ty.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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

}
