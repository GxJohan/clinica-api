package com.mycompany.clinicadental.controller;

import com.mycompany.clinicadental.model.Cita;
import com.mycompany.clinicadental.model.Dentista;
import com.mycompany.clinicadental.model.Paciente;
import com.mycompany.clinicadental.repository.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mycompany.clinicadental.exception.ResourceNotFoundException;
import com.mycompany.clinicadental.dto.CitaDTO;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/clinicadental/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @GetMapping
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable(value = "id") Long citaId) {
        Cita cita = citaRepository.findById(citaId)
                .orElse(null);
        if (cita == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cita);
    }

    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody CitaDTO citaDTO) {
        try {
            Paciente paciente = pacienteRepository.findById(citaDTO.getPacienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + citaDTO.getPacienteId()));

            Dentista dentista = dentistaRepository.findById(citaDTO.getDentistaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Dentista no encontrado con id: " + citaDTO.getDentistaId()));

            Cita cita = new Cita();
            cita.setPaciente(paciente);
            cita.setDentista(dentista);
            cita.setFechaHora(citaDTO.getFechaHora());
            cita.setMotivo(citaDTO.getMotivo());

            Cita savedCita = citaRepository.save(cita);
            return ResponseEntity.ok(savedCita);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable(value = "id") Long citaId,
                                           @RequestBody CitaDTO citaDTO) {
        try {
            Cita cita = citaRepository.findById(citaId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con id: " + citaId));

            Paciente paciente = pacienteRepository.findById(citaDTO.getPacienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id: " + citaDTO.getPacienteId()));

            Dentista dentista = dentistaRepository.findById(citaDTO.getDentistaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Dentista no encontrado con id: " + citaDTO.getDentistaId()));

            cita.setPaciente(paciente);
            cita.setDentista(dentista);
            cita.setFechaHora(citaDTO.getFechaHora());
            cita.setMotivo(citaDTO.getMotivo());

            Cita updatedCita = citaRepository.save(cita);
            return ResponseEntity.ok(updatedCita);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable(value = "id") Long citaId) {
        try {
            Cita cita = citaRepository.findById(citaId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con id: " + citaId));

            citaRepository.delete(cita);
            return ResponseEntity.noContent().build();  // Devuelve 204 No Content
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
}
}
