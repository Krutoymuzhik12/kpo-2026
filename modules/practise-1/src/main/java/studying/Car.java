package studying;

import lombok.Getter;

/**
 * Инновационный педальный автомобиль.
 * Двигатель — часть автомобиля (композиция): Car сам создаёт Engine,
 * наружу ссылку на него не отдаёт, поэтому автомобиля без двигателя не бывает.
 */
public class Car {
    private final Engine engine;

    @Getter
    private final int number;

    public Car(int number, int pedalSize) {
        this.number = number;
        this.engine = new Engine(pedalSize);
    }

    @Override
    public String toString() {
        return "Car{number=" + number + ", " + engine + "}";
    }
}
