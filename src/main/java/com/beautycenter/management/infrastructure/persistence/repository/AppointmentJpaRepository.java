package com.beautycenter.management.infrastructure.persistence.repository;

import com.beautycenter.management.domain.model.AppointmentStatus;
import com.beautycenter.management.infrastructure.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Spring Data JPA repository for AppointmentEntity.
 */
@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, Long> {
    
    /**
     * Find appointments by customer ID.
     *
     * @param customerId the customer ID
     * @return list of appointments for the customer
     */
    List<AppointmentEntity> findByCustomerId(Long customerId);
    
    /**
     * Find appointments by employee ID.
     *
     * @param employeeId the employee ID
     * @return list of appointments for the employee
     */
    List<AppointmentEntity> findByEmployeeId(Long employeeId);
    
    /**
     * Find appointments by company ID.
     *
     * @param companyId the company ID
     * @return list of appointments for the company
     */
    List<AppointmentEntity> findByCompanyId(Long companyId);
    
    /**
     * Find appointments by status.
     *
     * @param status the appointment status
     * @return list of appointments with the specified status
     */
    List<AppointmentEntity> findByStatus(AppointmentStatus status);
    
    /**
     * Find appointments by company ID and status.
     *
     * @param companyId the company ID
     * @param status the appointment status
     * @return list of appointments for the company with the specified status
     */
    List<AppointmentEntity> findByCompanyIdAndStatus(Long companyId, AppointmentStatus status);
    
    /**
     * Find appointments between start and end times.
     *
     * @param start the start time
     * @param end the end time
     * @return list of appointments in the time range
     */
    List<AppointmentEntity> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    
    /**
     * Find appointments by employee ID between start and end times.
     *
     * @param employeeId the employee ID
     * @param start the start time
     * @param end the end time
     * @return list of appointments for the employee in the time range
     */
    List<AppointmentEntity> findByEmployeeIdAndStartTimeBetween(Long employeeId, LocalDateTime start, LocalDateTime end);
    
    /**
     * Find appointments by company ID between start and end times.
     *
     * @param companyId the company ID
     * @param start the start time
     * @param end the end time
     * @return list of appointments for the company in the time range
     */
    List<AppointmentEntity> findByCompanyIdAndStartTimeBetween(Long companyId, LocalDateTime start, LocalDateTime end);
}