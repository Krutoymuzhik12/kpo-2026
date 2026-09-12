package studying;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FactoryAFTest {

    @Test
    void saleCarGivesOneCarToEachCustomer() {
        var factory = new FactoryAF();
        factory.addCar(1);
        factory.addCar(2);

        var vasya = new Customer("Вася");
        var vova = new Customer("Вова");
        factory.addCustomer(vasya);
        factory.addCustomer(vova);

        factory.saleCar();

        assertTrue(vasya.hasCar());
        assertTrue(vova.hasCar());
        assertNotSame(vasya.getCar(), vova.getCar());
    }

    @Test
    void leftoverCarsAreDestroyed() {
        var factory = new FactoryAF();
        factory.addCar(1);
        factory.addCar(2);
        factory.addCar(3);

        var vasya = new Customer("Вася");
        factory.addCustomer(vasya);

        factory.saleCar();

        assertTrue(vasya.hasCar());
        // склад пуст: один автомобиль продан, остальные ликвидированы
        assertTrue(captureCars(factory).isBlank());
    }

    @Test
    void customersWithoutCarsStayInQueueWhenStockRunsOut() {
        var factory = new FactoryAF();
        factory.addCar(1);

        var vasya = new Customer("Вася");
        var sveta = new Customer("Света");
        factory.addCustomer(vasya);
        factory.addCustomer(sveta);

        factory.saleCar();

        assertTrue(vasya.hasCar());
        assertFalse(sveta.hasCar());
    }

    @Test
    void carsGetSequentialNumbers() {
        var factory = new FactoryAF();
        factory.addCar(1);
        factory.addCar(2);

        var first = new Customer("Вася");
        var second = new Customer("Вова");
        factory.addCustomer(first);
        factory.addCustomer(second);
        factory.saleCar();

        assertEquals(1, first.getCar().getNumber());
        assertEquals(2, second.getCar().getNumber());
    }

    @Test
    void engineRequiresPositivePedalSize() {
        assertThrows(IllegalArgumentException.class, () -> new Engine(0));
    }

    private String captureCars(FactoryAF factory) {
        var out = new java.io.ByteArrayOutputStream();
        var original = System.out;
        System.setOut(new java.io.PrintStream(out, true, java.nio.charset.StandardCharsets.UTF_8));
        try {
            factory.printCars();
        } finally {
            System.setOut(original);
        }
        return out.toString(java.nio.charset.StandardCharsets.UTF_8).replace("Склад пуст", "").trim();
    }
}
