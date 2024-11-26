package rosa.victor.rental.client

import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.reactive.RestPath
import rosa.victor.rental.model.Rental

@Path("/rental")
@RegisterRestClient(baseUri = "http://localhost:8082")
interface RentalClient {

    @POST
    @Path("/start/{userId}/{reservationId}")
    fun start(
        @RestPath("userId") userId: String,
        @RestPath("reservationId") reservationId: Long
    ): Rental
}