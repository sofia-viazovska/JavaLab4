package transport;

import java.util.ArrayList;
import java.util.List;

/**
 * Дописаний варіант класу з умови (Simple Task 4.1, додатковий пункт).
 *
 * carsInRoad типізований як List<Vehicle<?>> (необмежений wildcard):
 * на дорозі одночасно їдуть Vehicle<Human>, Vehicle<Firefighter>,
 * Vehicle<PoliceOfficer> тощо - Road не потребує знати, ЯКИЙ саме тип
 * пасажирів возить кожен конкретний засіб, йому важлива лише кількість
 * людей у ньому, тому конкретний параметр типу тут не фіксується.
 */
public class Road {

    public List<Vehicle<?>> carsInRoad = new ArrayList<>();

    /** Сумарна кількість людей, що перевозяться всіма авто на цій ділянці дороги. */
    public int getCountOfHumans() {
        int total = 0;
        for (Vehicle<?> car : carsInRoad) {
            total += car.getOccupiedSeats();
        }
        return total;
    }

    /**
     * Додає транспортний засіб на дорогу.
     * Параметр оголошений як Vehicle<? extends Human> - обмежений (bounded)
     * wildcard "extends": метод лише ЧИТАЄ переданий об'єкт (додає його в
     * список), тобто виступає "споживачем" (Consumer) у термінах PECS
     * (Producer Extends, Consumer Super) - так само, як у прикладі з
     * завдання: boolean addAll(Collection<? extends E> c). Завдяки цьому
     * сюди можна передати і Bus (Vehicle<Human>), і PoliceCar
     * (Vehicle<PoliceOfficer>), і FireTruck (Vehicle<Firefighter>) - без
     * додаткового приведення типів.
     */
    public void addCarToRoad(Vehicle<? extends Human> car) {
        carsInRoad.add(car);
    }
}
