package com.beautycenter.management.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Configuration for the event system.
 * Enables asynchronous event processing in Spring.
 */
@Configuration
@EnableAsync
public class EventConfig {
}