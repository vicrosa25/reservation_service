package rosa.victor.inventory.client

import jakarta.inject.Singleton
import rosa.victor.inventory.model.Car

@Singleton
class InMemoryInventoryClient : InventoryClient {

    companion object {
        private final val ALL_CARS = listOf(
            Car(1L, "ABC-123", "Toyota", "Corolla"),
            Car(2L, "ABC-987", "Honda", "Jazz"),
            Car(3L, "XYZ-123", "Renault", "Clio"),
            Car(4L, "XYZ-987", "Ford", "Focus")
        );
    }

    override fun findAllCars(): List<Car> {
        return ALL_CARS
    }

}