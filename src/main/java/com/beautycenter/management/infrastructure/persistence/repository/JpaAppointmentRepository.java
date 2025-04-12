package com.beautycenter.management.infrastructure.persistence.repository;

import com.beautycenter.management.infrastructure.persistence.entity.AppointmentEntity;
import com.beautycenter.management.infrastructure.persistence.entity.CustomerEntity;
import com.beautycenter.management.infrastructure.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for AppointmentEntity.
 */
@Repository
public interface JpaAppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {

    List<AppointmentEntity> findByCustomer(CustomerEntity customer);
    
    List<AppointmentEntity> findByEmployee(EmployeeEntity employee);
    
    List<AppointmentEntity> findByCompanyId(UUID companyId);
    
    List<AppointmentEntity> findByStatus(String status);
    
    List<AppointmentEntity> findByCompanyIdAndStatus(UUID companyId, String status);
    
    List<AppointmentEntity> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    
    List<AppointmentEntity> findByEmployeeIdAndStartTimeBetween(UUID employeeId, 
                                                               LocalDateTime start, 
                                                               LocalDateTime end);
    
    List<AppointmentEntity> findByCompanyIdAndStartTimeBetween(UUID companyId, 
                                                              LocalDateTime start, 
                                                              LocalDateTime end);
    
    @Query("SELECT a FROM AppointmentEntity a WHERE a.companyId = :companyId " +
           "AND a.employee.id = :employeeId " +
           "AND ((a.startTime <= :endTime AND a.endTime >= :startTime) " +
           "OR (a.startTime >= :startTime AND a.startTime < :endTime))")
    List<AppointmentEntity> findOverlappingAppointments(@Param("companyId") UUID companyId,
                                                       @Param("employeeId") UUID employeeId,
                                                       @Param("startTime") LocalDateTime startTime,
                                                       @Param("endTime") LocalDateTime endTime);
}