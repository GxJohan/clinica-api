package com.mycompany.clinicadental.dto;

import java.time.LocalDateTime;

public class CitaDTO {
    private Long pacienteId;
    private Long dentistaId;
    private LocalDateTime fechaHora;
    private String motivo;

    // Constructor sin argumentos
    public CitaDTO() {}

    // Getters y setters
    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getDentistaId() {
        return dentistaId;
    }

    public void setDentistaId(Long dentistaId) {
        this.dentistaId = dentistaId;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}