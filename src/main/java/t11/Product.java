package t11;


import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String name;
    private BigDecimal price;

    Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    void changePrice(BigDecimal newPrice) {
        if ((newPrice.compareTo(BigDecimal.ZERO) < 0 || newPrice.remainder(BigDecimal.valueOf(0.01d)).compareTo(BigDecimal.ZERO) != 0)) throw new IllegalArgumentException();
        this.price = newPrice;
    }

    void changeName(String newName){
        this.name = newName;
    }

    BigDecimal getPrice() {
        return price;
    }

    private String getName() {
        return name;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Product) {
            Product other = (Product) obj;
            return name.equals(other.name) && price.equals(other.price);
        }
        return false;
    }

    @Override
    public String toString() {
        return ("name: " + this.getName() +
                "   price: " + this.getPrice());
    }

    @Override
    public int hashCode() {
        return (Objects.hash(name, price));
    }

}