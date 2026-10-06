package transport;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class VehicleTest {

    @Test
    public void busAcceptsAnyPassengerType() {
        Bus bus = new Bus(3);
        bus.boardPassenger(new Human("Оля"));
        bus.boardPassenger(new Firefighter("Максим"));
        bus.boardPassenger(new PoliceOfficer("Ігор"));

        assertEquals(3, bus.getOccupiedSeats());
        assertEquals(3, bus.getMaxSeats());
    }

    @Test
    public void taxiAcceptsAnyPassengerType() {
        Taxi taxi = new Taxi(4);
        taxi.boardPassenger(new Human("Оля"));
        taxi.boardPassenger(new Firefighter("Максим"));

        assertEquals(2, taxi.getOccupiedSeats());
    }

    @Test
    public void policeCarAcceptsOnlyPoliceOfficers() {
        PoliceCar car = new PoliceCar(2);
        car.boardPassenger(new PoliceOfficer("Ігор"));
        // car.boardPassenger(new Firefighter("Максим")); // НЕ скомпілюється -
        // саме це обмеження generics і вимагалось за умовою

        assertEquals(1, car.getOccupiedSeats());
    }

    @Test
    public void fireTruckAcceptsOnlyFirefighters() {
        FireTruck truck = new FireTruck(2);
        truck.boardPassenger(new Firefighter("Максим"));

        assertEquals(1, truck.getOccupiedSeats());
    }

    @Test(expected = IllegalStateException.class)
    public void boardingFullVehicleThrows() {
        PoliceCar car = new PoliceCar(1);
        car.boardPassenger(new PoliceOfficer("Ігор"));
        car.boardPassenger(new PoliceOfficer("Данило")); // другого місця немає
    }

    @Test(expected = NoSuchElementException.class)
    public void dropOffPassengerNotInVehicleThrows() {
        Bus bus = new Bus(2);
        bus.dropOffPassenger(new Human("Хтось, хто не заходив"));
    }

    @Test
    public void dropOffFreesASeat() {
        Bus bus = new Bus(1);
        Human passenger = new Human("Оля");

        bus.boardPassenger(passenger);
        bus.dropOffPassenger(passenger);
        assertEquals(0, bus.getOccupiedSeats());

        bus.boardPassenger(new Human("Інший"));
        assertEquals(1, bus.getOccupiedSeats());
    }
}
