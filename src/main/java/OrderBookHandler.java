import order.Order;
import order.Side;

import java.util.List;

public interface OrderBookHandler {

    void addOrder(Order order);

    void cancelOrder(long orderId);

    List<String> getSummary(Side side);

    static OrderBookHandler createInstance() {
        return new OrderBoardHandlerImpl();
    }
}
