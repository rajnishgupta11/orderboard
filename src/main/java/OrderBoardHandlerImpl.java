import com.google.common.annotations.VisibleForTesting;
import order.Order;
import order.Side;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class OrderBoardHandlerImpl implements OrderBookHandler {

    //Declare map [orderId -> order]
    private final Map<Long, Order> orderIdToOrderMap;
    //Declare map for buy side  [price ->  buyOrdersSum]
    private final Map<BigDecimal, BigDecimal> priceToBuyOrdersQtySumMap;
    //Declare map for buy side  [price ->  sellOrdersSum]
    private final Map<BigDecimal, BigDecimal> priceToSellOrdersQtySumMap;

    private static final String  FOR_CURRENCY_SYMBOL = " for £";

    OrderBoardHandlerImpl() {
        orderIdToOrderMap   =  new HashMap<>();
        priceToBuyOrdersQtySumMap =  new TreeMap<>(Collections.reverseOrder()); //sorted based on decreasing order price
        priceToSellOrdersQtySumMap =  new TreeMap<>(); //sorted based on increasing order price

    }

    public void addOrder(Order currentOrder) {
        //Edge cases
        validateOrder(currentOrder);

        //Update buy or sell side map based on type of currentOrder
        if(Side.BUY.equals(currentOrder.getSide())) {
            priceToBuyOrdersQtySumMap.compute(currentOrder.getPrice(), (k, v) -> (v == null) ? currentOrder.getQuantity() : v.add(currentOrder.getQuantity()));
        } else if(Side.SELL.equals(currentOrder.getSide())) {
            priceToSellOrdersQtySumMap.compute(currentOrder.getPrice(), (k, v) -> (v == null) ? currentOrder.getQuantity() : v.add(currentOrder.getQuantity()));
        }

        //update orderIdToOrderMap
        orderIdToOrderMap.put(currentOrder.getOrderId(), currentOrder);
    }

    private void validateOrder(Order currentOrder) {
        //Edge cases
        if(currentOrder == null) {
            throw new IllegalArgumentException("Null order");
        }
        if(orderIdToOrderMap.containsKey(currentOrder.getOrderId())) {
            throw new IllegalArgumentException("Order already processed");
        }
        if(currentOrder.getQuantity().compareTo(BigDecimal.ZERO) <0 ) {
            throw new IllegalArgumentException("Negative order quantity");
        }
        if(currentOrder.getPrice().compareTo(BigDecimal.ZERO) <0 ) {
            throw new IllegalArgumentException("Negative order price");
        }
    }

    public void cancelOrder(long orderId) {
        final Order existingOrder = orderIdToOrderMap.get(orderId);
        //Edge cases
        if(existingOrder==null) {
            throw new IllegalArgumentException("Order does not exist");
        }

        if(Side.BUY.equals(existingOrder.getSide())) {
            priceToBuyOrdersQtySumMap.compute(existingOrder.getPrice(), (k, v) -> v == null ? null : v.subtract(existingOrder.getQuantity()));
        }  else if(Side.SELL.equals(existingOrder.getSide())) {
            priceToSellOrdersQtySumMap.compute(existingOrder.getPrice(), (k, v) -> v == null ? null : v.subtract(existingOrder.getQuantity()));
        }

        //update orderIdToOrderMap
        orderIdToOrderMap.remove(orderId);
    }

    public List<String> getSummary(Side side) {
        if(Side.BUY.equals(side)) {
            return transform(priceToBuyOrdersQtySumMap);

        } else if(Side.SELL.equals(side)){
            return transform(priceToSellOrdersQtySumMap);
        }
        return Collections.emptyList();
    }

    private List<String> transform(Map<BigDecimal, BigDecimal> priceToBuyOrdersQtySumMap) {
        return priceToBuyOrdersQtySumMap.entrySet().stream()
                .map(e -> e.getValue() + FOR_CURRENCY_SYMBOL + e.getKey())
                .collect(Collectors.toList());
    }

    @VisibleForTesting
    protected Map<Long, Order> getOrderIdToOrderMap() {
        return orderIdToOrderMap;
    }

    @VisibleForTesting
    protected Map<BigDecimal, BigDecimal> getPriceToBuyOrdersQtySumMap() {
        return priceToBuyOrdersQtySumMap;
    }

    @VisibleForTesting
    protected Map<BigDecimal, BigDecimal> getPriceToSellOrdersQtySumMap() {
        return priceToSellOrdersQtySumMap;
    }
}
