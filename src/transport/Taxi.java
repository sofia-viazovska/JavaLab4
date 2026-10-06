package transport;

/** Таксі перевозить будь-яких пасажирів. */
public class Taxi extends Car<Human> {
    public Taxi(int maxSeats) {
        super(maxSeats);
    }
}
