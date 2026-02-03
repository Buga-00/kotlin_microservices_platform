package no.kristiania.orderservice.model

import javax.persistence.*

@Entity
@Table(name = "orderfor")
class Order (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_sequence")
    @SequenceGenerator(
        name = "order_id_sequence",
        allocationSize = 1
    )
    @Column(name = "id")
    val id: Int? = null,

    @Column(name = "name")
    val name: String? = null,
)