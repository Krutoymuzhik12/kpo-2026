package studying;

import lombok.Getter;

/**
 * Желающий приобрести педальный автомобиль.
 * Живёт независимо от автомобиля: ссылка на Car — агрегация,
 * она может быть пустой, пока очередь не дошла.
 */
@Getter
public class Customer {
    private final String name;

    private Car car;

    public Customer(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("ФИО не может быть пустым");
        }
        this.name = name;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean hasCar() {
        return car != null;
    }

    @Override
    public String toString() {
        return "Customer{name=" + name + ", car=" + (car == null ? "нет" : car) + "}";
    }
}
