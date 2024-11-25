package rosa.victor.model

import java.time.LocalDate

data class Reservation(
    var id: Long = 0L,
    var userId: String = "",
    var carId: Long = 0L,
    var startDay: LocalDate = LocalDate.now(),
    var endDay: LocalDate = LocalDate.now(),
) {

    fun isReserved(
        startDate: LocalDate,
        endDate: LocalDate,
    ): Boolean = (!startDate.isAfter(endDate) || startDate.isBefore(endDate))
}
