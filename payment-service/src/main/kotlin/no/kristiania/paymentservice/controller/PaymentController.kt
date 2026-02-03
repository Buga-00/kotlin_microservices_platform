 package no.kristiania.paymentservice.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import no.kristiania.paymentservice.integration.ShippingIntegrationService

@RestController
@RequestMapping("/api/payment")
class PaymentController(@Autowired private val shippingIntegrationService: ShippingIntegrationService) {

    @GetMapping
    fun getShipHello(): ResponseEntity<String> {
        return ResponseEntity.ok("Argh, we be pirate ships")
    }

    @PostMapping("/{message}")
    fun createShipMessage(@PathVariable message: String) {
    }

    @PostMapping("/callToShip")
    fun getResponseFromShipService() {
        shippingIntegrationService.sendHttpCallToShipService("This is a message from the payment controller service")
    }

    //for senere?
    @GetMapping("/http/{message}")
    fun responseToOrderService(@PathVariable message: String): ResponseEntity<String> {
        println(message)
        return ResponseEntity.ok("Hello from payment service, thank you for your request. the payment has been made $message")
    }

}