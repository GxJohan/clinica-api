package com.mycompany.clinicadental.repository;

import com.mycompany.clinicadental.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistaRepository extends JpaRepository<Dentista,Long>{
}
