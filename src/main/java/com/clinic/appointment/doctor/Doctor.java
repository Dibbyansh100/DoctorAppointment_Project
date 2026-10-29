package com.clinic.appointment.doctor;

import java.util.List;

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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Doctor {
	@Id
	@SequenceGenerator(name= "doctor_id_generator",initialValue = 1001, allocationSize =1,sequenceName = "doc_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_id_generator")
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(unique = true,nullable = false)
	private String email;
	@Column(unique = true, nullable = false, length =10)
	private String phone;
	@Enumerated(EnumType.STRING)
	private Specalization specilization;
	private Double consultationFee;
	private Boolean active = true;
	
	@OneToMany(mappedBy = "doctor")
	private List<Appointment> appointments;

}
