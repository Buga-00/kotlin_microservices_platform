package no.kristiania.orderservice.integration

import no.kristiania.orderservice.model.Order
import no.kristiania.orderservice.service.OrderService
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Import(OrderService::class)
class OrderIntegrationDatabaseTests(@Autowired private val orderService: OrderService) {


    @Test
    fun shouldGetOrder(){

        val newOrder = getTestOrderDatabaseList()[0]
        val newOrder2 = getTestOrderDatabaseList()[0]
        val newOrder3 = getTestOrderDatabaseList()[0]

        orderService.createOrder2(newOrder)
        orderService.createOrder2(newOrder2)
        orderService.createOrder2(newOrder3)

        val result = orderService.getAllOrder()
        assert(result.size <= 3)
    }

    @Test
    fun shouldRegisterAndFindOrder(){

        val newOrder = getTestOrderDatabaseList()[0]
        val createdOrder = orderService.createOrder2(newOrder)

        assert(createdOrder?.name == newOrder.name)

        val foundAnimal = newOrder.name?.let { orderService.getOrdersByName(it)?.get(0) }

        assert(foundAnimal?.name == createdOrder?.name)
    }

    @Test
    fun shouldDeleteOrder(){

        val newOrder = getTestOrderDatabaseList()[0]
        orderService.createOrder2(newOrder)

        assert(orderService.deleteOrder(1) )
    }


    @Test
    fun shouldUpdateOrder(){

        val newOrder = getTestOrderDatabaseList()[0]
        val createdOrder = orderService.createOrder2(newOrder)

        assert(createdOrder?.name == newOrder.name)

        val orderUpdate = getTestOrderDatabaseList()[0]

        orderService.updateOrderStatus(1, orderUpdate)

        val order = orderService.getOrderById(1);

        assert(order?.name == orderUpdate.name)

    }


    fun getTestOrderDatabaseList() : List<Order> {

        return listOf(
            Order(0,"Big Apple")
        )
    }

    fun getTestOrderDatabaseListToUpdate() : List<Order> {

        return listOf(
            Order(0,"Mac")
        )
    }

}






fun getTestAnimalList(): List<Order> {

    return listOf(
        Order(0, "fish"),
        Order(1, "milk"),
        Order(2, "water"),
        Order(3, "Is Cream"),
        Order(4, "Juice")
    )
}