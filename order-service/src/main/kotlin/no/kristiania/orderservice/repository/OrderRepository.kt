package no.kristiania.orderservice.repository

import no.kristiania.orderservice.model.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: PagingAndSortingRepository<Order, Int> {

    override fun findAll(pageable: Pageable): Page<Order>

    fun findByNameIgnoreCase(name: String) : List<Order>

}