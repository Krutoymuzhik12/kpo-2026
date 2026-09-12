package studying;

import java.util.ArrayList;
import java.util.List;

/**
 * Предприятие автотранспортного факультета НИУ ВШЭ.
 * Автомобили — композиция: фабрика сама их производит (addCar) и сама
 * распоряжается их временем жизни, наружу коллекция не отдаётся.
 * Покупатели — агрегация: они создаются вне фабрики и переживают её.
 */
public class FactoryAF {
    private final List<Car> cars = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();

    private int producedCars;

    /** Производит новый автомобиль: он изначально принадлежит НИУ ВШЭ. */
    public void addCar(int pedalSize) {
        cars.add(new Car(++producedCars, pedalSize));
    }

    /** Ставит покупателя в очередь. */
    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Покупатель не может быть null");
        }
        customers.add(customer);
    }

    /**
     * Проходит по очереди и вручает каждому безлошадному покупателю автомобиль,
     * пока они есть на складе. Выданный автомобиль убирается со склада.
     * Если после раздачи автомобили остались — они ликвидируются.
     */
    public void saleCar() {
        for (Customer customer : customers) {
            if (cars.isEmpty()) {
                break;
            }
            if (!customer.hasCar()) {
                customer.setCar(cars.removeFirst());
            }
        }

        if (!cars.isEmpty()) {
            System.out.println("Не выкупленные автомобили ликвидированы: " + cars.size() + " шт.");
            cars.clear();
        }
    }

    public void printCars() {
        if (cars.isEmpty()) {
            System.out.println("Склад пуст");
            return;
        }
        cars.forEach(System.out::println);
    }

    public void printCustomers() {
        if (customers.isEmpty()) {
            System.out.println("Очередь пуста");
            return;
        }
        customers.forEach(System.out::println);
    }
}
