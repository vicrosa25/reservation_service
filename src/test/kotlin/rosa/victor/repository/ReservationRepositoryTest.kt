package rosa.victor.repository

import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import rosa.victor.model.Reservation
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@QuarkusTest
class ReservationRepositoryTest {

    @Inject
    lateinit var reservationRepository: ReservationRepository

    @Test
    fun testCreateReservation(): Unit {
        val reservation = Reservation()
        reservation.startDay = LocalDate.now().plus(5, ChronoUnit.DAYS)
        reservation.endDay = LocalDate.now().plus(12, ChronoUnit.DAYS)
        reservation.carId = 384L
        reservationRepository.save(reservation)

        assertNotNull(reservation.carId)
        assertTrue(reservationRepository.findAll().contains(reservation))
    }
}