package rosa.victor.repository

import rosa.victor.model.Reservation

interface ReservationRepository {

    fun findAll(): List<Reservation>

    fun save(reservation: Reservation): Reservation
}