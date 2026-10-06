package transport;

public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus(20);
        bus.boardPassenger(new Human("Оля"));
        bus.boardPassenger(new Firefighter("Максим")); // автобус бере будь-кого
        bus.boardPassenger(new PoliceOfficer("Ігор"));
        System.out.println(bus + " -> " + bus.getOccupiedSeats() + " пасажирів");

        PoliceCar policeCar = new PoliceCar(2);
        policeCar.boardPassenger(new PoliceOfficer("Данило"));
        // policeCar.boardPassenger(new Firefighter("Максим")); // не скомпілюється
        System.out.println(policeCar);

        FireTruck fireTruck = new FireTruck(4);
        fireTruck.boardPassenger(new Firefighter("Максим"));
        System.out.println(fireTruck);

        Road road = new Road();
        road.addCarToRoad(bus);
        road.addCarToRoad(policeCar);
        road.addCarToRoad(fireTruck);
        System.out.println("Всього людей на дорозі: " + road.getCountOfHumans());
    }
}
