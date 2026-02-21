package com.company.auth.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // Ejemplo: Un buzón para avisar que un usuario se registró
    public static final String AUTH_QUEUE = "auth.user.created";

    @Bean
    public Queue authQueue() {
        return new Queue(AUTH_QUEUE, true); // true = el buzón sobrevive si Rabbit se apaga
    }
}
