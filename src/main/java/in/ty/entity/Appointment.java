package in.ty.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {
	
	private Long id;
	private LocalDateTime appointmentDateTime;
	private String reason;
	private AppointmentStatus appointmentStatus;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;

}
