package t11;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

class PriceListTest {

    private PriceList actual = new PriceList();

    private Product normal = new Product("name", BigDecimal.valueOf(11.10));
    private Product alsoNormal = new Product("name", BigDecimal.valueOf(22.22));
    private Product negativePriced = new Product("name", BigDecimal.valueOf(-11.1));
    private Product overPriced = new Product("name", BigDecimal.valueOf(11.111));

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    void addProduct() {
        assertTrue(actual.addProduct(1, normal));

        try {
            actual.addProduct(1, normal);
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

        try {
            actual.addProduct(2, negativePriced);
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

        try {
            actual.addProduct(3, overPriced);
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

    }

    @Test
    void removeProduct() {
        actual.addProduct(1, normal);

        assertTrue(actual.removeProduct(1));

        try {
            actual.removeProduct(1);
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

    }

    @Test
    void changePrice() {
        actual.addProduct(1, normal);

        assertTrue(actual.changePrice(1, BigDecimal.valueOf(1.10)));

        try {
            actual.changePrice(0, BigDecimal.valueOf(1.10));
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

        try {
            actual.changePrice(1, BigDecimal.valueOf(-1.0));
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

        try {
            actual.changePrice(3, BigDecimal.valueOf(1.111));
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    void changeName() {
        actual.addProduct(1, normal);

        assertTrue(actual.changeName(1, "Normal"));

        try {
            actual.changeName(0, "NoSuchCode");
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

    }

    @Test
    void countCost() {
        int[] codes = {1,2};
        int[] quants = {10,10};
        int[] wrongCodes = {0,1};
        int[] wrongQuants = {10,10,5};

        actual.addProduct(1,normal);
        actual.addProduct(2,alsoNormal);

        Assert.assertEquals(BigDecimal.valueOf(333.20).setScale(2), actual.countCost(codes, quants));

        try {
            actual.countCost(codes, wrongQuants);
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

        try {
            actual.countCost(wrongCodes, quants);
        } catch (IllegalArgumentException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }

    }
}