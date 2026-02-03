package no.kristiania.shippingservice.repository

import no.kristiania.shippingservice.model.Shipping
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingRepository: PagingAndSortingRepository<Shipping, Int> {

    override fun findAll(pageable: Pageable): Page<Shipping>

    fun findByNameIgnoreCase(name: String) : List<Shipping>
}