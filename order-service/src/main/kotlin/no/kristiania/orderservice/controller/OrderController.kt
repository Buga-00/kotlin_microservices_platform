package no.kristiania.orderservice.controller

import no.kristiania.orderservice.integration.ShippingIntegrationService
import no.kristiania.orderservice.integration.RabbitSender
import no.kristiania.orderservice.model.Order
import no.kristiania.orderservice.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/api/order")
class OrderController(
    @Autowired private val rabbitSender: RabbitSender,
    @Autowired private val shippingIntegrationService: ShippingIntegrationService,
    @Autowired private val orderService: OrderService
) {

    @GetMapping
    fun getOrderHello(): ResponseEntity<String> {
        return ResponseEntity.ok("We are order service")
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Int): ResponseEntity<Order> {
        return ResponseEntity.ok(orderService.getOrderById(id))
    }

    @GetMapping("/all")
    fun getOrders(@PathParam("page") page: Int): ResponseEntity<List<Order>> {
        return ResponseEntity.ok(orderService.getOrder(page).toList())
    }

    @GetMapping("/alls")
    fun getOrders(): ResponseEntity<List<Order>> {
        return ResponseEntity.ok(orderService.getAllOrder())
    }

    @PostMapping("/create")
    fun createOrder(@RequestBody order: Order): ResponseEntity<Int> {
        System.out.println(order.name.toString())
        return ResponseEntity.ok(orderService.createOrder(order))
    }


    @PostMapping("/{message}")
    fun createOrderMessage(@PathVariable message: String) {
        //add to database eple with id 4
        //blir betalt
        //hvis ikke feil så send videre til shipment
        rabbitSender.sendMessage(message)
    }

    @PostMapping("/callToPayment")
    fun getResponseFromPaymentService() {
        shippingIntegrationService.sendHttpCallToPaymentService(", This is a message from the order Controller service")
    }

}