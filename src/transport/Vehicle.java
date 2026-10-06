package transport;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Узагальнений (generic) базовий клас транспортного засобу.
 * Параметр типу T - обмежений (bounded) через "T extends Human": це і є
 * саме той механізм generics, який на рівні компіляції задає обмеження,
 * яким саме пасажирам дозволено їздити в конкретному підкласі:
 *  - Vehicle<Human>          -> приймає будь-якого пасажира (Bus, Taxi)
 *  - Vehicle<Firefighter>    -> приймає тільки пожежників (FireTruck)
 *  - Vehicle<PoliceOfficer>  -> приймає тільки поліцейських (PoliceCar)
 *
 * @param <T> тип (або спільний предок типів) пасажирів, яких приймає цей засіб
 */
public abstract class Vehicle<T extends Human> {

    private final int maxSeats;
    private final List<T> passengers = new ArrayList<>();

    protected Vehicle(int maxSeats) {
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("Кількість місць має бути додатною: " + maxSeats);
        }
        this.maxSeats = maxSeats;
    }

    /** Максимальна кількість місць. */
    public int getMaxSeats() {
        return maxSeats;
    }

    /** Кількість зайнятих місць. */
    public int getOccupiedSeats() {
        return passengers.size();
    }

    /** Посадка пасажира. Кидає IllegalStateException, якщо вільних місць немає. */
    public void boardPassenger(T passenger) {
        if (passengers.size() >= maxSeats) {
            throw new IllegalStateException("Усі місця зайнято (" + maxSeats + ")");
        }
        passengers.add(passenger);
    }

    /** Висадка пасажира. Кидає NoSuchElementException, якщо цей пасажир не в засобі. */
    public void dropOffPassenger(T passenger) {
        if (!passengers.remove(passenger)) {
            throw new NoSuchElementException("Пасажир " + passenger + " не перебуває у цьому транспортному засобі");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + getOccupiedSeats() + "/" + maxSeats + ")";
    }
}
