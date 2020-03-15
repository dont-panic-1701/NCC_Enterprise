package t11;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

public class PriceList {
    private HashMap<Integer, Product> priceList = new HashMap<>();


    boolean addProduct(int code, Product product) {
        BigDecimal price = product.getPrice();
        if ((price.compareTo(BigDecimal.ZERO) < 0 || price.remainder(BigDecimal.valueOf(0.01d)).compareTo(BigDecimal.ZERO) != 0)) throw new IllegalArgumentException();
        if (priceList.containsKey(code)) throw new IllegalArgumentException();
        priceList.put(code, product);
        return true;
    }

    boolean removeProduct(int code) {
        if (!priceList.containsKey(code)) throw new IllegalArgumentException();
        priceList.remove(code);
        return true;
    }

    boolean changePrice(int code, BigDecimal newPrice) {
        if (!priceList.containsKey(code)) throw new IllegalArgumentException();
        priceList.get(code).changePrice(newPrice);
        return true;
    }

    boolean changeName(int code, String newName) {
        if (!priceList.containsKey(code)) throw new IllegalArgumentException();
        priceList.get(code).changeName(newName);
        return true;
    }

    BigDecimal countCost(int[] codes, int[] quants) {
        if (codes.length != quants.length) throw new IllegalArgumentException();
        BigDecimal cost = BigDecimal.ZERO;
        for (int i = 0; i < codes.length; i++) {
            if (priceList.containsKey(codes[i]) && quants[i] > 0)
                cost = cost.add(priceList.get(codes[i]).getPrice().multiply(BigDecimal.valueOf(quants[i])));
            else throw new IllegalArgumentException();
        }
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof PriceList) {
            PriceList other = (PriceList) obj;
            return priceList.equals(other.priceList);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        priceList.forEach((Integer, Item) -> result.append("code: ").append(Integer).append("   ").
                append("item: ").append(Item).append("\n"));
        return result.toString();
    }

    @Override
    public int hashCode() {
        return (Objects.hash(priceList));
    }

}

