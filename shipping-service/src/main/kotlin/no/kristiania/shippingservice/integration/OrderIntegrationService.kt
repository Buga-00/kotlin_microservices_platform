package no.kristiania.shippingservice.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Service
class OrderIntegrationService(@Autowired private val restTemplate: RestTemplate) {

    val shipUrl = "http://order-service/api/order/http"

    fun sendHttpCallToOrderService(message: String) {
        val response: ResponseEntity<String> = restTemplate.getForEntity("$shipUrl/$message", ResponseEntity::class)
        println("response from ship integration service: ${response.body}")
    }
}