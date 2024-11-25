package rosa.victor.inventory.client

import rosa.victor.inventory.model.Car

interface InventoryClient {

    fun findAllCars(): List<Car>
}