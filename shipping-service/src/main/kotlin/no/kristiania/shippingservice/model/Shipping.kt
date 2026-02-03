package no.kristiania.shippingservice.model

import javax.persistence.Entity
import javax.persistence.*

@Entity
@Table(name = "shippingfor")
class Shipping (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_id_sequence")
    @SequenceGenerator(
        name = "shipping_id_sequence",
        allocationSize = 1
    )
    @Column(name = "id")
    val id: Int? = null,

    @Column(name = "name")
    val name: String? = null,

    )