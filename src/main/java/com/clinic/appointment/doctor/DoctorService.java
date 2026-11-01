package com.clinic.appointment.doctor;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.clinic.appointment.doctor.dto.DoctorRequest;
import com.clinic.appointment.doctor.dto.DoctorResponse;
import com.clinic.appointment.exception.BusinessRuleViolationException;
import com.clinic.appointment.exception.DuplicateResourceException;
import com.clinic.appointment.exception.ResourceNotFoundException;
import com.clinic.appointment.patient.dto.ResponseStructure;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorResponse mapToResponse(Doctor doctor) {
        return new DoctorResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getSpecilization(),
                doctor.getConsultationFee(),
                doctor.getActive()
        );
    }

    public Doctor getDoctorEntityById(UUID id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
    }

    public ResponseStructure<DoctorResponse> createDoctor(DoctorRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();

        if (doctorRepository.existsByEmail(normalizedEmail)) {
            throw new DuplicateResourceException("Doctor already exists with email: " + request.email());
        }

        if (request.consultationFee().compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessRuleViolationException("Consultation fee cannot be negative.");
        }

        Doctor doctor = new Doctor();
        doctor.setName(request.name());
        doctor.setEmail(normalizedEmail);        
        doctor.setPhone(request.phone());
        doctor.setSpecilization(request.specilization());
        doctor.setConsultationFee(request.consultationFee());
        doctor.setActive(true);

        Doctor doctorSaved = doctorRepository.save(doctor);
        DoctorResponse res = mapToResponse(doctorSaved) ;
        return new ResponseStructure<DoctorResponse>(HttpStatus.CREATED.value(),"Doctor created successfully",res);
    }
}
