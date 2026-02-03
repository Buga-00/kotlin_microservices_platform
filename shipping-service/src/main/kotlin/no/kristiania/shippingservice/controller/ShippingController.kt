package no.kristiania.shippingservice.controller

import no.kristiania.shippingservice.integration.OrderIntegrationService
import no.kristiania.shippingservice.integration.RabbitSender
import no.kristiania.shippingservice.model.Shipping
import no.kristiania.shippingservice.service.ShippingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/api/shipping")
class ShippingController(
    @Autowired private val rabbitSender: RabbitSender,
    @Autowired private val orderIntegrationService: OrderIntegrationService,
    @Autowired private val shippingService: ShippingService
    ) {

    @GetMapping
    fun getShipHello(): ResponseEntity<String> {
        return ResponseEntity.ok("We are shipping service")
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Int): ResponseEntity<Shipping> {
        return ResponseEntity.ok(shippingService.getShippingById(id))
    }

    @GetMapping("/alls")
    fun getOrders(): ResponseEntity<List<Shipping>> {
        return ResponseEntity.ok(shippingService.getAllShipping())
    }

    @GetMapping("/all")
    fun getOrders(@PathParam("page") page: Int): ResponseEntity<List<Shipping>> {
        return ResponseEntity.ok(shippingService.getShipping(page).toList())
    }

    @PostMapping("/create")
    fun createOrder(@RequestBody shipping: Shipping): ResponseEntity<Int> {
        System.out.println(shipping.name.toString())
        return ResponseEntity.ok(shippingService.createShipping(shipping))
    }

    @PostMapping("/{message}")
    fun createShipMessage(@PathVariable message: String) {
        rabbitSender.sendMessage(message)
    }

    @PostMapping("/callToOrder")
    fun getResponseFromPaymentService() {
        orderIntegrationService.sendHttpCallToOrderService(", This is a message from the shipping Controller service")
    }


}