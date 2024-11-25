package rosa.victor.controller

import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestQuery
import rosa.victor.inventory.model.Car
import rosa.victor.model.Reservation
import rosa.victor.service.ReservationService
import java.time.LocalDate

@Path("reservation")
@Produces(MediaType.APPLICATION_JSON)
class ReservationController(
    private final val reservationService: ReservationService
) {

    @GET
    @Path("availability")
    fun availability(
        @RestQuery startDate: LocalDate,
        @RestQuery endDate: LocalDate,
    ): Collection<Car> =
        reservationService.getAvailableCars(startDate, endDate)


    @POST
    fun createReservation(reservation: Reservation): Reservation =
        reservationService.createReservation(reservation)
}