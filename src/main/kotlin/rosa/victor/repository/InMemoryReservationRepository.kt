package rosa.victor.repository

import jakarta.inject.Singleton
import rosa.victor.model.Reservation
import java.util.Collections
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicLong

@Singleton
class InMemoryReservationRepository : ReservationRepository {

    private final val ids: AtomicLong = AtomicLong(0)
    private final val store: CopyOnWriteArrayList<Reservation> = CopyOnWriteArrayList()

    override
    fun findAll(): List<Reservation>  = Collections.unmodifiableList(store)

    override
    fun save(reservation: Reservation): Reservation {
        reservation.id = ids.getAndIncrement()
        store.add(reservation)
        return reservation;
    }
}