/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicadental.repository;

import com.mycompany.clinicadental.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author C27444
 */
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    
}
