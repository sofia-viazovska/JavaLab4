package transport;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoadTest {

    @Test
    public void countsHumansAcrossDifferentVehicleTypes() {
        Road road = new Road();

        Bus bus = new Bus(20);
        bus.boardPassenger(new Human("Оля"));
        bus.boardPassenger(new Human("Настя"));

        PoliceCar policeCar = new PoliceCar(2);
        policeCar.boardPassenger(new PoliceOfficer("Ігор"));

        FireTruck fireTruck = new FireTruck(4);
        fireTruck.boardPassenger(new Firefighter("Максим"));
        fireTruck.boardPassenger(new Firefighter("Данило"));

        road.addCarToRoad(bus);
        road.addCarToRoad(policeCar);
        road.addCarToRoad(fireTruck);

        assertEquals(5, road.getCountOfHumans());
        assertEquals(3, road.carsInRoad.size());
    }

    @Test
    public void emptyRoadHasZeroHumans() {
        assertEquals(0, new Road().getCountOfHumans());
    }
}
