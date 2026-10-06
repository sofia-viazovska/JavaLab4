package transport;

/** Поліцейська машина перевозить тільки поліцейських (перевірено компілятором). */
public class PoliceCar extends Car<PoliceOfficer> {
    public PoliceCar(int maxSeats) {
        super(maxSeats);
    }
}
