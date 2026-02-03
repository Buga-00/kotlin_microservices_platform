package no.kristiania.orderservice.service

import no.kristiania.orderservice.model.Order
import no.kristiania.orderservice.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service



@Service
@CacheConfig(cacheNames = ["orders"])
class OrderService(@Autowired private val orderRepository: OrderRepository) {

    fun createOrder(name: Order): Int? {
        return orderRepository.save(name).id
    }

    @Cacheable(key = "#id")
    fun getOrderById(id: Int): Order? {
        return orderRepository.findByIdOrNull(id)
    }

    fun getOrder(page: Int): Page<Order> {
        return orderRepository.findAll(Pageable.ofSize(5).withPage(page))
    }


    fun getAllOrder(): List<Order> {
        return orderRepository.findAll() as List<Order>;
    }

    fun createOrder2(name: Order): Order? {
        return orderRepository.save(name)
    }

    fun getOrdersByName(name: String): List<Order>? {
        return orderRepository.findByNameIgnoreCase(name)
    }

    fun deleteOrder(id: Int): Boolean {
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id)
            return true
        }
        return false
    }

    @CachePut(key = "#order.id")
    fun updateOrderStatus(id: Int, name: Order): Boolean {
        orderRepository.findById(id).orElse(null)?.let {
        val newOrder = Order(
            id = it.id,
            name = it.name,
        )
        orderRepository.save(newOrder)
        return true
        }
        return false
    }

}