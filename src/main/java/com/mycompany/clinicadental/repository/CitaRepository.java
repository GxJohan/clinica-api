package com.mycompany.clinicadental.repository;
import com.mycompany.clinicadental.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita,Long> {
}
