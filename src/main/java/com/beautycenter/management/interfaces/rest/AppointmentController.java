package com.beautycenter.management.interfaces.rest;

import com.beautycenter.management.application.dto.AppointmentDto;
import com.beautycenter.management.application.service.AppointmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing appointments.
 * This is part of the interfaces layer in the hexagonal architecture.
 */
@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;

    /**
     * Create a new appointment.
     *
     * @param appointmentDto the appointment data
     * @return the created appointment
     */
    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto createdAppointment = appointmentService.createAppointmentFromDto(appointmentDto);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    /**
     * Get an appointment by ID.
     *
     * @param id the appointment ID
     * @return the appointment, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable UUID id) {
        return appointmentService.findByIdAsDto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all appointments.
     *
     * @return list of all appointments
     */
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.findAllAppointmentsAsDto();
        return ResponseEntity.ok(appointments);
    }

    /**
     * Update an appointment.
     *
     * @param id the appointment ID
     * @param appointmentDto the updated appointment data
     * @return the updated appointment, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable UUID id, 
                                                         @RequestBody AppointmentDto appointmentDto) {
        if (!id.equals(appointmentDto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        try {
            AppointmentDto updatedAppointment = appointmentService.updateAppointmentFromDto(appointmentDto);
            return ResponseEntity.ok(updatedAppointment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete an appointment.
     *
     * @param id the appointment ID
     * @return 204 if deleted, 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable UUID id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cancel an appointment.
     *
     * @param id the appointment ID
     * @return the cancelled appointment, or 404 if not found
     */
    @PostMapping("/{id}/cancel")
    public ResponseEntity<AppointmentDto> cancelAppointment(@PathVariable UUID id) {
        try {
            AppointmentDto appointment = appointmentService.cancelAppointmentAsDto(id);
            return ResponseEntity.ok(appointment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Complete an appointment.
     *
     * @param id the appointment ID
     * @return the completed appointment, or 404 if not found
     */
    @PostMapping("/{id}/complete")
    public ResponseEntity<AppointmentDto> completeAppointment(@PathVariable UUID id) {
        try {
            AppointmentDto appointment = appointmentService.completeAppointmentAsDto(id);
            return ResponseEntity.ok(appointment);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get appointments for a specific date.
     *
     * @param date the date in ISO format (yyyy-MM-dd)
     * @return list of appointments for the date
     */
    @GetMapping("/by-date/{date}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByDate(@PathVariable String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            List<AppointmentDto> appointments = appointmentService.findAppointmentsByDate(parsedDate)
                    .stream()
                    .map(appointment -> appointmentService.findByIdAsDto(appointment.getId())
                            .orElse(null))
                    .filter(dto -> dto != null)
                    .toList();
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}