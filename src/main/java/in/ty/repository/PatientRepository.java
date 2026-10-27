package in.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ty.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
