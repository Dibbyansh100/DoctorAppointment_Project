package in.ty.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.ty.dto.PatientRequest;
import in.ty.dto.PatientResponse;
import in.ty.dto.ResponseStructure;
import in.ty.entity.Patient;
import in.ty.repository.PatientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {
	private final PatientRepository patientRepository;
	 public ResponseStructure<PatientResponse> createPatient(PatientRequest request) {
		 Patient patient = new Patient();
		 patient.setName(request.name());
		 patient.setAge(request.age());
		 patient.setPhone(request.phone());
		 patient.setAddress( request.address());
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
}
