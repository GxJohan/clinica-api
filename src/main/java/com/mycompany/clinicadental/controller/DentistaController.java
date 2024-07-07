package com.mycompany.clinicadental.controller;

import com.mycompany.clinicadental.model.Dentista;
import com.mycompany.clinicadental.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/clinicadental/dentistas")
public class DentistaController {

    @Autowired
    private DentistaRepository dentistaRepository;

    @GetMapping
    public List<Dentista> getAllDentistas() {
        return dentistaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentista> getDentistaById(@PathVariable(value = "id") Long dentistaId) {
        Dentista dentista = dentistaRepository.findById(dentistaId)
                .orElse(null);
        if (dentista == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(dentista);
    }

    @PostMapping
    public Dentista createDentista(@RequestBody Dentista dentista) {
        return dentistaRepository.save(dentista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dentista> updateDentista(@PathVariable(value = "id") Long dentistaId,
                                                   @RequestBody Dentista dentistaDetails) {
        Dentista dentista = dentistaRepository.findById(dentistaId)
                .orElse(null);
        if (dentista == null) {
            return ResponseEntity.notFound().build();
        }
        dentista.setNombre(dentistaDetails.getNombre());
        dentista.setApellido(dentistaDetails.getApellido());
        dentista.setEspecialidad(dentistaDetails.getEspecialidad());
        Dentista updatedDentista = dentistaRepository.save(dentista);
        return ResponseEntity.ok(updatedDentista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentista(@PathVariable(value = "id") Long dentistaId) {
        Dentista dentista = dentistaRepository.findById(dentistaId)
                .orElse(null);
        if (dentista == null) {
            return ResponseEntity.notFound().build();
        }
        dentistaRepository.delete(dentista);
        return ResponseEntity.ok().build();
    }

}
