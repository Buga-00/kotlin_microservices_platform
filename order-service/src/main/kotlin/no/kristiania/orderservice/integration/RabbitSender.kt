package no.kristiania.orderservice.integration

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.stereotype.Service

@Service
@EnableAutoConfiguration
class RabbitSender(@Autowired private val rabbitTemplate: RabbitTemplate) {

    fun sendMessage(message: String) {
        rabbitTemplate.convertAndSend("ship_queue", message)
    }
}