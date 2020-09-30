package order;

import java.math.BigDecimal;
import java.util.Objects;

public final class Order {

    private final long orderId;
    private final String userId;
    private final CoinType coinType;
    private final Side side;
    private final BigDecimal quantity;
    private final BigDecimal price;

    public Order(long orderId, String userId, CoinType coinType, Side side, BigDecimal quantity, BigDecimal price) {
        this.orderId = orderId;
        this.userId = userId;
        this.coinType = coinType;
        this.side = side;
        this.quantity = quantity;
        this.price = price;
    }


    public long getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public CoinType getCoinType() {
        return coinType;
    }

    public Side getSide() {
        return side;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }


    //TODO check which fields to include
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
