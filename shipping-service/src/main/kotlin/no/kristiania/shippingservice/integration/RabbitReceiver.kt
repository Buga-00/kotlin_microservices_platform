package no.kristiania.shippingservice.integration

import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["ship_queue"])
class RabbitReceiver(@Autowired private val rabbitSender: RabbitSender) {

    @RabbitHandler
    fun receive(message: String) {
        println("Received: $message from ship queue")
        rabbitSender.sendMessage("we are working on the order $message packet")
    }


}