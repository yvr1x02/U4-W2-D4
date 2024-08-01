package yuriimpellizzeri;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        //elenco prodotti
        Product product1 = new Product(1234, "Hacklog vol1", "Book", 325.00);
        Product product2 = new Product(4567, "Hacklog vol2", "Book", 135.00);
        Product product3 = new Product(8901, "Elden Ring", "Boys", 60.80);
        Product product4 = new Product(2345, "Hollow Knight", "Boys", 25.80);
        Product product5 = new Product(6789, "Mario Kart 8", "Baby", 75.00);


        //elenco customer
        Customer customer1 = new Customer(14567, "Aldo", 1);
        Customer customer2 = new Customer(28901, "Giovanni", 2);
        Customer customer3 = new Customer(37654, "Giacomo", 3);

        // elenco ordini
        Order order1 = new Order(9876, "shipped", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7, 3), Arrays.asList(product1, product3), customer1);
        Order order2 = new Order(5432, "delivered", LocalDate.of(2024, 7, 10), LocalDate.of(2024, 7, 15), Arrays.asList(product2, product4), customer2);
        Order order3 = new Order(1098, "processing", LocalDate.of(2024, 7, 20), LocalDate.of(2024, 7, 25), Arrays.asList(product3, product5), customer1);
        Order order4 = new Order(2345, "shipped", LocalDate.of(2024, 7, 30), LocalDate.of(2024, 7, 5), Arrays.asList(product2, product3), customer3);

        //lista prodotti
        List<Product> products = Arrays.asList(product1, product2, product3, product4, product5);

        //lista ordini

        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        // ottengo gli ordini in base all utente

        System.out.println("----Es 1----");

        Map<Customer, List<Order>> ordersByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));


        ordersByCustomer.forEach((customer, orderList) -> {
            System.out.println("Customer: " + customer.getName());
            orderList.forEach(order -> System.out.println("Order ID: " + order.getId() + " Status: " + order.getStatus()));
        });


        System.out.println("----Es 2----");


        Map<Customer, Double> totalSalesByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer,
                        Collectors.summingDouble(order -> order.getProducts().stream()
                                .mapToDouble(Product::getPrice)
                                .sum())));


        totalSalesByCustomer.forEach((customer, totalSales) -> System.out.println("Utente: " + customer.getName() + ", Spesa Totale: " + totalSales));

       
    }
}




