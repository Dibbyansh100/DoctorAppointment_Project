package in.ty.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import in.ty.dto.PatientPartialRequest;
import in.ty.dto.PatientRequest;
import in.ty.dto.PatientResponse;
import in.ty.dto.ResponseStructure;
import in.ty.entity.Address;
import in.ty.entity.Patient;
import in.ty.repository.PatientRepository;
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
	// API 2: Get All Patients
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
}
