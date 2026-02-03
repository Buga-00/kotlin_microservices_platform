package no.kristiania.paymentservice.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Service
class ShippingIntegrationService(@Autowired private val restTemplate: RestTemplate) {


    //note that we are using the name of the service in eureka, order-service, instead of the localhost:port
    val shipUrl = "http://ship-service/api/ship/http"

    fun sendHttpCallToShipService(message: String) {
        val response: ResponseEntity<String> = restTemplate.getForEntity("$shipUrl/$message", ResponseEntity::class)
        println("response from ship integration service: ${response.body}")
    }

}