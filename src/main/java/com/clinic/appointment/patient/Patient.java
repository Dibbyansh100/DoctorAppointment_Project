package com.clinic.appointment.patient;

import java.util.List;
import java.util.UUID;

import com.clinic.appointment.address.Address;
import com.clinic.appointment.appointment.Appointment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
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
