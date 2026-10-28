package in.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ty.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
