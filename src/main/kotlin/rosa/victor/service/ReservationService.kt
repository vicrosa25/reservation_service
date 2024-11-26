package rosa.victor.service

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.resteasy.reactive.RestQuery
import rosa.victor.inventory.client.InventoryClient
import rosa.victor.inventory.model.Car
import rosa.victor.model.Reservation
import rosa.victor.rental.client.RentalClient
import rosa.victor.repository.ReservationRepository
import java.time.LocalDate

@ApplicationScoped
class ReservationService(
    val inventoryClient: InventoryClient,
    val reservationRepository: ReservationRepository,
    @RestClient val rentalClient: RentalClient
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
        val result =  reservationRepository.save(reservation)
        val userId = "x" // TODO change with real data
        if (reservation.startDay == LocalDate.now()) {
            rentalClient.start(userId, result.id)
        }
        return result
    }

}