package com.beautycenter.management.application.event;

import com.beautycenter.management.domain.event.AppointmentCreatedEvent;
import com.beautycenter.management.domain.event.AppointmentStatusChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Event handler for appointment-related events
 */
@Component
public class AppointmentEventHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(AppointmentEventHandler.class);
    
    /**
     * Handle appointment creation events
     * 
     * @param event the created event
     */
    @EventListener
    @Async
    public void handleAppointmentCreated(AppointmentCreatedEvent event) {
        logger.info("Handling appointment creation: Appointment ID={}, Company ID={}",
                event.getAppointmentId(), event.getCompanyId());
        
        // Implement notification or other side effects here
        // For example, send confirmation email to customer
    }
    
    /**
     * Handle appointment status change events
     * 
     * @param event the status changed event
     */
    @EventListener
    @Async
    public void handleAppointmentStatusChanged(AppointmentStatusChangedEvent event) {
        logger.info("Handling appointment status change: Appointment ID={}, Old status={}, New status={}",
                event.getAppointmentId(), event.getOldStatus(), event.getNewStatus());
        
        // Implement notification logic here
        switch (event.getNewStatus()) {
            case CONFIRMED:
                sendConfirmationNotification(event);
                break;
            case CANCELLED:
                sendCancellationNotification(event);
                break;
            case COMPLETED:
                sendCompletionNotification(event);
                break;
            default:
                // No specific action for other statuses
                break;
        }
    }
    
    private void sendConfirmationNotification(AppointmentStatusChangedEvent event) {
        // Implementation for sending confirmation notifications
        logger.info("Sending confirmation notification for appointment: {}", event.getAppointmentId());
    }
    
    private void sendCancellationNotification(AppointmentStatusChangedEvent event) {
        // Implementation for sending cancellation notifications
        logger.info("Sending cancellation notification for appointment: {}", event.getAppointmentId());
    }
    
    private void sendCompletionNotification(AppointmentStatusChangedEvent event) {
        // Implementation for sending completion notifications
        logger.info("Sending completion notification for appointment: {}", event.getAppointmentId());
    }
}