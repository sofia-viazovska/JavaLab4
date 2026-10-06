package transport;

/**
 * Базовий клас людини-пасажира. Екземпляр самого Human - це "звичайний
 * пасажир" (Рис. 1); Firefighter і PoliceOfficer - спеціалізовані підкласи.
 */
public class Human {

    private final String name;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + name + ")";
    }
}
