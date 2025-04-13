package com.beautycenter.management.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Configuration for asynchronous processing
 */
@Configuration
@EnableAsync
public class AsyncConfiguration {
    // Additional async executor configuration can be added here if needed
}