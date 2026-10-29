package com.clinic.appointment.patient;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.appointment.patient.dto.PatientPartialRequest;
import com.clinic.appointment.patient.dto.PatientRequest;
import com.clinic.appointment.patient.dto.PatientResponse;
import com.clinic.appointment.patient.dto.ResponseStructure;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PatientController {
	private final PatientService patientService;
	
	// 1. Create Patient
	@ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/patient")
    public ResponseStructure<PatientResponse> createPatient(@Valid @RequestBody PatientRequest request) {
        return patientService.createPatient(request);
    }
	//2.UpdatePatient
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PatchMapping("/patient/{update}")
	public ResponseStructure<PatientResponse> PatientPartialUpdate(@PathVariable PatientPartialRequest request,Long  Id){
		return patientService.PatientPartialUpdation(request, Id);
	}
	//3.GetAll Patient
	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/patient")
    public ResponseStructure<List<PatientResponse>> getAllPatient() {
        return patientService.getAllPatients();
        }
	//4.GetPatientById
	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/patient/{id}")
    public ResponseStructure<PatientResponse> getPatientById(@PathVariable Long patientId) {
        return patientService.getPatientById(patientId);
        }
	//5.DeActivatePatient
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/patient/{id}/deactivate")
	public ResponseStructure<PatientResponse> deactivatePatient(@PathVariable  Long patientId){
		return patientService.deactivatePatient(patientId);
	}
	//6.ReActivatePatient
	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/patient/{id}/activate")
	public ResponseStructure<PatientResponse> activatePatient(@PathVariable Long patientId){
		return patientService.ActivatePatient(patientId);
	}

}
