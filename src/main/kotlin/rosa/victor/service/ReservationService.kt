package rosa.victor.service

import jakarta.enterprise.context.ApplicationScoped
import org.jboss.resteasy.reactive.RestQuery
import rosa.victor.inventory.client.InventoryClient
import rosa.victor.inventory.model.Car
import rosa.victor.model.Reservation
import rosa.victor.repository.ReservationRepository
import java.time.LocalDate

@ApplicationScoped
class ReservationService(
    private final val inventoryClient: InventoryClient,
    private final val reservationRepository: ReservationRepository,
) {

    fun getAvailableCars(startDate: LocalDate, endDate: LocalDate, ): List<Car> {
        // 1. Get all cars
        val cars = inventoryClient.findAllCars()

        // 2. Create a map from id to car
        var carsById = cars.associateBy { it.id }.toMutableMap()

        // 3. Filter reserved cars.
        reservationRepository.findAll()
            .forEach { reservation ->
                if (reservation.isReserved(startDate, endDate)) {
                    carsById.remove(reservation.carId)
                }
            }
        return carsById.values.toList()
    }

    fun createReservation(reservation: Reservation): Reservation {
        return reservationRepository.save(reservation)
    }

}