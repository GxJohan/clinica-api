/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicadental.controller;
import com.mycompany.clinicadental.model.Paciente;
import com.mycompany.clinicadental.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mycompany.clinicadental.exception.ResourceNotFoundException;

import java.util.List;


/**
 *
 * @author C27444
 */
@RestController
@RequestMapping("/clinicadental/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable(value = "id") Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElse(null);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(paciente);
    }

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable(value = "id") Long pacienteId,
                                                   @RequestBody Paciente pacienteDetails) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElse(null);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        paciente.setNombre(pacienteDetails.getNombre());
        paciente.setApellido(pacienteDetails.getApellido());
        paciente.setFechaNacimiento(pacienteDetails.getFechaNacimiento());
        paciente.setTelefono(pacienteDetails.getTelefono());
        Paciente updatedPaciente = pacienteRepository.save(paciente);
        return ResponseEntity.ok(updatedPaciente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable(value = "id") Long pacienteId) {
        try {
            Paciente paciente = pacienteRepository.findById(pacienteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + pacienteId));

            pacienteRepository.delete(paciente);
            return ResponseEntity.noContent().build();  // Devuelve 204 No Content
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
