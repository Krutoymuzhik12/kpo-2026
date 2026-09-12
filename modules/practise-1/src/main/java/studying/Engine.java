package studying;

import lombok.Getter;

/**
 * Революционный педальный двигатель.
 * Существует только как часть автомобиля (композиция): создаётся внутри Car
 * и уничтожается вместе с ним.
 */
@Getter
public class Engine {
    public static final String TYPE = "PEDAL";

    private final int pedalSize;

    public Engine(int pedalSize) {
        if (pedalSize <= 0) {
            throw new IllegalArgumentException("Размер педалей должен быть положительным");
        }
        this.pedalSize = pedalSize;
    }

    @Override
    public String toString() {
        return "Engine{type=" + TYPE + ", pedalSize=" + pedalSize + "}";
    }
}
