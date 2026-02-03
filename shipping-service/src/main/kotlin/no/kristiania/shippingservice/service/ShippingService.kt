package no.kristiania.shippingservice.service

import no.kristiania.shippingservice.model.Shipping
import no.kristiania.shippingservice.repository.ShippingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@CacheConfig(cacheNames = ["shippings"])
class ShippingService(@Autowired private val shippingRepository: ShippingRepository) {

    fun createShipping(name: Shipping): Int? {
        return shippingRepository.save(name).id
    }

    @Cacheable(key = "#id")
    fun getShippingById(id: Int): Shipping? {
        return shippingRepository.findByIdOrNull(id)
    }

    fun getShipping(page: Int): Page<Shipping> {
        return shippingRepository.findAll(Pageable.ofSize(5).withPage(page))
    }


    fun getAllShipping(): List<Shipping> {
        return shippingRepository.findAll() as List<Shipping>;
    }

    fun createShipping2(name: Shipping): Shipping? {
        return shippingRepository.save(name)
    }

    fun getShippingsByName(name: String): List<Shipping>? {
        return shippingRepository.findByNameIgnoreCase(name)
    }

    fun deleteShipping(id: Int): Boolean {
        if(shippingRepository.existsById(id)){
            shippingRepository.deleteById(id)
            return true
        }
        return false
    }

    @CachePut(key = "#shipping.id")
    fun updateShippingStatus(id: Int, name: Shipping): Boolean {
        shippingRepository.findById(id).orElse(null)?.let {
            val newShipping = Shipping(
                id = it.id,
                name = it.name,
            )
            shippingRepository.save(newShipping)
            return true
        }
        return false
    }
}