package no.kristiania.orderservice.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

import org.springframework.web.client.getForEntity


@Service
class ShippingIntegrationService(@Autowired private val restTemplate: RestTemplate) {

    //note that we are using the name of the service in eureka, ship-service, instead of the localhost:port
    val shipUrl = "http://payment-service/api/payment/http"

    fun sendHttpCallToPaymentService(message: String) {
        val response: ResponseEntity<String> = restTemplate.getForEntity("$shipUrl/$message", ResponseEntity::class)
        println("response from payment integration service: ${response.body}")
    }

}