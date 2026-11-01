package com.clinic.appointment.doctor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.clinic.appointment.appointment.Appointment;
import com.clinic.appointment.appointment.Specalization;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(nullable = false)
	private String name;
	@Column(unique = true,nullable = false)
	private String email;
	@Column(unique = true, nullable = false, length =10)
	private String phone;
	@Enumerated(EnumType.STRING)
	private Specalization specilization;
	@Column(precision = 10, scale = 2)
	private BigDecimal consultationFee;
	private Boolean active = true;
	
	@OneToMany(mappedBy = "doctor")
	private List<Appointment> appointments;

}
