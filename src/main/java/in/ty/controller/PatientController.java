package in.ty.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.ty.dto.PatientRequest;
import in.ty.dto.PatientResponse;
import in.ty.dto.ResponseStructure;
import in.ty.service.PatientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PatientController {
	private final PatientService patientService;
	
	// 1. Create Patient
	@ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/patient")
    public ResponseStructure<PatientResponse> createPatient(@RequestBody PatientRequest request) {
        return patientService.createPatient(request);
    }

}
