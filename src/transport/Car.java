package transport;

/** Проміжний клас "Автомобіль" (Рис. 1) - Таксі, ПоліцейськаМашина, ПожежнаМашина. */
public abstract class Car<T extends Human> extends Vehicle<T> {
    protected Car(int maxSeats) {
        super(maxSeats);
    }
}
