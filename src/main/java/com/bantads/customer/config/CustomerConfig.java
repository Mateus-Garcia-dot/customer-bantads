package com.bantads.customer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(RabbitMqConfig.class)
@Configuration
public class CustomerConfig {

    static final String createQueueName = "customer.create";
    static final String updateQueueName = "customer.update";
    static final String deleteQueueName = "customer.delete";

    @Bean
    public Queue createQueueCreate() {
        return new Queue(createQueueName, true);
    }

    @Bean
    public Queue updateQueueUpdate() {
        return new Queue(updateQueueName, true);
    }

    @Bean
    public Queue deleteQueueDelete() {
        return new Queue(deleteQueueName, true);
    }

    @Bean
    Binding createBinding(Queue createAccountQueue, DirectExchange exchange) {
        return BindingBuilder.bind(createAccountQueue).to(exchange).with(createQueueName);
    }

    @Bean
    Binding updateBinding(Queue createAccountQueue, DirectExchange exchange) {
        return BindingBuilder.bind(createAccountQueue).to(exchange).with(updateQueueName);
    }

    @Bean
    Binding deleteBinding(Queue createAccountQueue, DirectExchange exchange) {
        return BindingBuilder.bind(createAccountQueue).to(exchange).with(deleteQueueName);
    }

}
