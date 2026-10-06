package transport;

/** Пожежна машина перевозить тільки пожежників (перевірено компілятором). */
public class FireTruck extends Car<Firefighter> {
    public FireTruck(int maxSeats) {
        super(maxSeats);
    }
}
