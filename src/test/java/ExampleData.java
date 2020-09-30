import order.CoinType;
import order.Order;
import order.Side;

import java.math.BigDecimal;

public class ExampleData {


    public static void buildExampleOrderBoardWithOneAddOrder(OrderBookHandler orderBookHandler) {
        orderBookHandler.addOrder(new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125")));
    }

    public static void buildExampleOrderBoardWithSameOrderIdReAdded(OrderBookHandler orderBookHandler) {
        orderBookHandler.addOrder(new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125")));
        orderBookHandler.addOrder(new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125")));
    }

    public static void buildExampleOrderBoardWithNullOrder(OrderBookHandler orderBookHandler) {
        orderBookHandler.addOrder(null);
    }

    public static void buildExampleOrderBoardWithMultipleBuyAndSellOrdersAtSamePrice(OrderBookHandler orderBookHandler) {
        orderBookHandler.addOrder(new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125")));
        orderBookHandler.addOrder(new Order(2l, "userId1", CoinType.ETHEREUM, Side.BUY,new BigDecimal("1"), new BigDecimal("125")));

        orderBookHandler.addOrder(new Order(3l, "userId1", CoinType.LITECOIN, Side.SELL,new BigDecimal("1"), new BigDecimal("125")));
        orderBookHandler.addOrder(new Order(4l, "userId1", CoinType.ETHEREUM, Side.SELL,new BigDecimal("1"), new BigDecimal("125")));
    }

    public static void buildExampleOrderBoardWithMultipleBuyAndSellOrdersAtAllDifferentPrice(OrderBookHandler orderBookHandler) {
        orderBookHandler.addOrder(new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125")));
        orderBookHandler.addOrder(new Order(2l, "userId1", CoinType.ETHEREUM, Side.BUY,new BigDecimal("1"), new BigDecimal("126")));

        orderBookHandler.addOrder(new Order(3l, "userId1", CoinType.LITECOIN, Side.SELL,new BigDecimal("1"), new BigDecimal("127")));
        orderBookHandler.addOrder(new Order(4l, "userId1", CoinType.ETHEREUM, Side.SELL,new BigDecimal("1"), new BigDecimal("128")));
    }

    public static void buildExampleOrderBoardWithMultipleBuyAndSellOrdersWIthCombinationOfSameAndDifferentPrice(OrderBookHandler orderBookHandler) {
        orderBookHandler.addOrder(new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125")));
        orderBookHandler.addOrder(new Order(2l, "userId1", CoinType.ETHEREUM, Side.BUY,new BigDecimal("1"), new BigDecimal("125")));

        orderBookHandler.addOrder(new Order(3l, "userId1", CoinType.LITECOIN, Side.SELL,new BigDecimal("1"), new BigDecimal("127")));
        orderBookHandler.addOrder(new Order(4l, "userId1", CoinType.ETHEREUM, Side.SELL,new BigDecimal("1"), new BigDecimal("128")));
    }

    public static void buildExampleOrderBoardWithMultipleSellOrdersForDifferentUsers(OrderBookHandler orderBookHandler) {
        orderBookHandler.addOrder(new Order(1l, "userId1", CoinType.ETHEREUM, Side.SELL,new BigDecimal("350.1"), new BigDecimal("13.6")));
        orderBookHandler.addOrder(new Order(2l, "userId2", CoinType.ETHEREUM, Side.SELL,new BigDecimal("50.5"), new BigDecimal("14")));

        orderBookHandler.addOrder(new Order(3l, "userId3", CoinType.ETHEREUM, Side.SELL,new BigDecimal("441.8"), new BigDecimal("13.9")));
        orderBookHandler.addOrder(new Order(4l, "userId4", CoinType.ETHEREUM, Side.SELL,new BigDecimal("3.5"), new BigDecimal("13.6")));
    }

    public static void buildExampleOrderBoardWithMultipleBuyOrdersForDifferentUsers(OrderBookHandler orderBookHandler) {
        orderBookHandler.addOrder(new Order(1l, "userId1", CoinType.ETHEREUM, Side.BUY,new BigDecimal("350.1"), new BigDecimal("13.6")));
        orderBookHandler.addOrder(new Order(2l, "userId2", CoinType.ETHEREUM, Side.BUY,new BigDecimal("50.5"), new BigDecimal("14")));

        orderBookHandler.addOrder(new Order(3l, "userId3", CoinType.ETHEREUM, Side.BUY,new BigDecimal("441.8"), new BigDecimal("13.9")));
        orderBookHandler.addOrder(new Order(4l, "userId4", CoinType.ETHEREUM, Side.BUY,new BigDecimal("3.5"), new BigDecimal("13.6")));
    }

  /*  public static void buildOrderWithAddOrder(OrderHandler orderHandler) {
        orderHandler.addOrder(new Order(7L, "MSFT", Side.BUY, 15, 20));
    }

    public static void buildOrderWithModifyOrder(OrderHandler orderHandler) {
        orderHandler.addOrder(new Order(6L, "MSFT", Side.BUY, 13, 5));
        orderHandler.modifyOrder(new OrderModification(6L, 15, 10));
    }

    public static void buildOrderWithAddAndRemoveOrder(OrderHandler orderHandler) {
        orderHandler.addOrder(new Order(7L, "MSFT", Side.BUY, 15, 20));
        orderHandler.cancelOrder(7L);
    }*/
}
