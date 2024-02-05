package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    ShopRepository repo = new ShopRepository();

    Product product1 = new Product(1, "Шашлык", 11);
    Product product2 = new Product(2, "Плов", 22);
    Product product3 = new Product(3, "Шаурма", 33);

    Product product4 = new Product(4, "стейк", 44);
    Product product5 = new Product(5, "Овощи", 55);

    Product product6 = new Product(6, "Чебурек", 66);

    @Test
    public void notFoundTest() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product5);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(4);
        });

    }

    @Test
    public void notFoundTest2() {
        Product[] products = {product1, product2, product3, product4, product5};
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);
        repo.add(product5);

        repo.remove(3);

        Product[] expected = {product1, product2, product4, product5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void alreadyExistsTest() {
        Product[] products = {product1, product2, product3, product4, product5};
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);
        repo.add(product5);

        repo.remove(3);

        repo.add(product6);

        Product[] expected = {product1, product2, product4, product5, product6};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldFindSameId() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);

        repo.remove(2);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product4);
        });
    }
}
