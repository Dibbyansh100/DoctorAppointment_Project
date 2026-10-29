package com.clinic.appointment.patient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.clinic.appointment.address.Address;
import com.clinic.appointment.patient.dto.PatientPartialRequest;
import com.clinic.appointment.patient.dto.PatientRequest;
import com.clinic.appointment.patient.dto.PatientResponse;
import com.clinic.appointment.patient.dto.ResponseStructure;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {
	private final PatientRepository patientRepository;
	
	//1.Create a Patient
	 public ResponseStructure<PatientResponse> createPatient(PatientRequest request) {
		 Patient patient = new Patient();
		 patient.setName(request.name());
		 patient.setAge(request.age());
		 patient.setPhone(request.phone());
		 // Create a new Address object
	        Address address = new Address();
	        address.setCity(request.address().getCity());
	        address.setHouseNumber(request.address().getHouseNumber());
	        address.setPincode(request.address().getPincode());

	        patient.setAddress(address);
		 Patient patientSaved = patientRepository.save(patient);
		 PatientResponse res = new PatientResponse(
				 patientSaved.getId(),
				 patientSaved.getName(),
				 patientSaved.getAge(),
				 patientSaved.getPhone(),
				 patientSaved.getAddress(),
				 patientSaved.isActive()
				 );
		 return new ResponseStructure<>(
			 HttpStatus.CREATED.value(),"Patient created successfully", res);
	 }
	 //2.Update a Patient 
	 public ResponseStructure<PatientResponse> PatientPartialUpdation (PatientPartialRequest request ,Long patientId){
		 Patient patient = new Patient ();
		 Patient patientSaved = patientRepository.findById(patientId).orElseThrow(() -> new RuntimeException("patient with id" + " " + patientId + "not exist"));
		 patient.setName(request.name().trim());
		 if(request.name().length()>1 && !(request.name().equals(patient.getName()))) {
			 patient.setName(request.name());
		 }
		 if(request.age()>0) {
			 patient.setAge(request.age());
		 }
		 if(request.phone().trim().length() == 10) {
			 patient.setPhone((request.phone().trim()));
		 }
		 patientRepository.save(patient);
		 PatientResponse res = new PatientResponse(
				 patient.getId(),
				 patient.getName(),
				 patient.getAge(),
				 patient.getPhone(),
				 patient.getAddress(),
				 patient.isActive()
		);
		 return new ResponseStructure<PatientResponse>(
			 HttpStatus.ACCEPTED.value(),"Record updated successfully",res);
	 }
	 //3.GET All Patient
	 public ResponseStructure<List<PatientResponse>> getAllPatients() {
	     
	     // 1. Retrieve all patients from the database
	     List<Patient> patients = patientRepository.findAll();
	     
	     // 2. Convert each entity into a response DTO using a loop
	     List<PatientResponse> responseList = new ArrayList<>();
	     for (Patient patient : patients) {
	         PatientResponse res = new PatientResponse(
	                 patient.getId(),
	                 patient.getName(),
	                 patient.getAge(),
	                 patient.getPhone(),
	                 patient.getAddress(),
	                 patient.isActive()
	         );
	         responseList.add(res);
	     }
	             
	     // 3. Return the list
	     return new ResponseStructure<>(
	             HttpStatus.OK.value(), 
	             "Patients retrieved successfully", 
	             responseList
	     );
	 }
	//3.GET PatientById
	// API 3: Get Patient by ID
	 public ResponseStructure<PatientResponse> getPatientById(Long patientId) {
	     
		// 1. Validate the Long ID
		    if (patientId == null || patientId <= 0) {
		        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Patient ID");
		    }

	     // 2. Find the patient & 3. Handle 404 if patient doesn't exist
	     Patient patient = patientRepository.findById(patientId)
	             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found with id: " + patientId));

	     // 4. Convert the entity to a response DTO
	     PatientResponse res = new PatientResponse(
	             patient.getId(),
	             patient.getName(),
	             patient.getAge(),
	             patient.getPhone(),
	             patient.getAddress(),
	             patient.isActive()
	     );

	     // Return the response structure
	     return new ResponseStructure<>(
	             HttpStatus.OK.value(),
	             "Patient retrieved successfully",
	             res
	     );
	 }
	// 5: Deactivate Patient
	 public ResponseStructure<PatientResponse> deactivatePatient(Long id) {

	     // 1. Find the patient (returns 404 if not found)
	     Patient patient = patientRepository.findById(id)
	             .orElseThrow(() -> new ResponseStatusException(
	                     HttpStatus.NOT_FOUND, 
	                     "Patient not found with id: " + id
	             ));

	     // 2. Set active to false
	     patient.setActive(false);

	     // 3. Save the change (Preserves all appointments automatically as no relationship modified)
	     Patient updatedPatient = patientRepository.save(patient);

	     // Convert to Response DTO
	     PatientResponse res = new PatientResponse(
	             updatedPatient.getId(),
	             updatedPatient.getName(),
	             updatedPatient.getAge(),
	             updatedPatient.getPhone(),
	             updatedPatient.getAddress(),
	             updatedPatient.isActive()
	     );

	     // Return response
	     return new ResponseStructure<>(
	             HttpStatus.OK.value(),
	             "Patient deactivated successfully",
	             res
	     );
	 }
	 //6.ActivatePatient
	 public ResponseStructure<PatientResponse> ActivatePatient(Long id) {

	     // 1. Find the patient (returns 404 if not found)
	     Patient patient = patientRepository.findById(id)
	             .orElseThrow(() -> new ResponseStatusException(
	                     HttpStatus.NOT_FOUND, 
	                     "Patient not found with id: " + id
	             ));

	     // 2. Set active to false
	     patient.setActive(true);

	     // 3. Save the change (Preserves all appointments automatically as no relationship modified)
	     Patient updatedPatient = patientRepository.save(patient);

	     // Convert to Response DTO
	     PatientResponse res = new PatientResponse(
	             updatedPatient.getId(),
	             updatedPatient.getName(),
	             updatedPatient.getAge(),
	             updatedPatient.getPhone(),
	             updatedPatient.getAddress(),
	             updatedPatient.isActive()
	     );

	     // Return response
	     return new ResponseStructure<>(
	             HttpStatus.OK.value(),
	             "Patient Activated successfully",
	             res
	     );
	 }
	 
}
