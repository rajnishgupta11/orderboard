import order.CoinType;
import order.Order;
import order.Side;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderHandlerTest {

    private OrderBoardHandlerImpl orderBookHandler;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        orderBookHandler = new OrderBoardHandlerImpl();
    }


    @Test
    public void testAddOrderWithOneBuyOrder()  {
        ExampleData.buildExampleOrderBoardWithOneAddOrder(orderBookHandler);
        Assert.assertEquals(1, orderBookHandler.getOrderIdToOrderMap().size());
        Assert.assertEquals(1, orderBookHandler.getPriceToBuyOrdersQtySumMap().size());
        Assert.assertEquals(0, orderBookHandler.getPriceToSellOrdersQtySumMap().size());
    }

    @Test
    public void testAddOrderWithMultipleBuyAndSellOrdersAtSamePrice()  {
        ExampleData.buildExampleOrderBoardWithMultipleBuyAndSellOrdersAtSamePrice(orderBookHandler);
        Assert.assertEquals(4, orderBookHandler.getOrderIdToOrderMap().size());
        Assert.assertEquals(1, orderBookHandler.getPriceToBuyOrdersQtySumMap().size());
        Assert.assertEquals(1, orderBookHandler.getPriceToSellOrdersQtySumMap().size());
    }

    @Test
    public void testAddOrderWithMultipleBuyAndSellOrdersAtDifferentPrice()  {
        ExampleData.buildExampleOrderBoardWithMultipleBuyAndSellOrdersAtAllDifferentPrice(orderBookHandler);
        Assert.assertEquals(4, orderBookHandler.getOrderIdToOrderMap().size());
        Assert.assertEquals(2, orderBookHandler.getPriceToBuyOrdersQtySumMap().size());
        Assert.assertEquals(2, orderBookHandler.getPriceToSellOrdersQtySumMap().size());
    }

    @Test
    public void testAddOrderWithMultipleBuyAndSellOrdersWIthCombinationOfSameAndDifferentPrice()  {
        ExampleData.buildExampleOrderBoardWithMultipleBuyAndSellOrdersWIthCombinationOfSameAndDifferentPrice(orderBookHandler);
        Assert.assertEquals(4, orderBookHandler.getOrderIdToOrderMap().size());
        Assert.assertEquals(1, orderBookHandler.getPriceToBuyOrdersQtySumMap().size());
        Assert.assertEquals(2, orderBookHandler.getPriceToSellOrdersQtySumMap().size());
    }


    @Test
    public void testAddOrderWithSameOrderIdReAdded_Invalid()  {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Order already processed");

        ExampleData.buildExampleOrderBoardWithSameOrderIdReAdded(orderBookHandler);
    }

    @Test
    public void testAddOrderWithNegativeOrderQuantity_Invalid()  {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Negative order quantity");

        Order order = new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("-1"), new BigDecimal("125"));
        orderBookHandler.addOrder(order);
    }

    @Test
    public void testAddOrderWithNegativeOrderPrice_Invalid()  {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Negative order price");

        Order order = new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("-125"));
        orderBookHandler.addOrder(order);
    }

    @Test
    public void testAddOrderNullOrder_Invalid()  {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Null order");

        ExampleData.buildExampleOrderBoardWithNullOrder(orderBookHandler);
    }

    @Test
    public void testCancelOrder_Invalid()  {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Order does not exist");

        Order order = new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125"));
        orderBookHandler.addOrder(order);

        Order nonExistentOrder = new Order(2l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125"));
        orderBookHandler.cancelOrder(nonExistentOrder.getOrderId());
    }


    @Test
    public void testCancelExistingBuyOrder()  {

        Order order = new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125"));
        orderBookHandler.addOrder(order);

        orderBookHandler.cancelOrder(order.getOrderId());
        Assert.assertEquals(0, orderBookHandler.getOrderIdToOrderMap().size());
        Assert.assertEquals( BigDecimal.ZERO, orderBookHandler.getPriceToBuyOrdersQtySumMap().values().stream().findFirst().get());
        Assert.assertEquals(0, orderBookHandler.getPriceToSellOrdersQtySumMap().size());
    }

    @Test
    public void testCancelExistingSellOrder()  {

        Order order = new Order(1l, "userId1", CoinType.LITECOIN, Side.SELL,new BigDecimal("1"), new BigDecimal("125"));
        orderBookHandler.addOrder(order);

        orderBookHandler.cancelOrder(order.getOrderId());
        Assert.assertEquals(0, orderBookHandler.getOrderIdToOrderMap().size());
        Assert.assertEquals(0, orderBookHandler.getPriceToBuyOrdersQtySumMap().size());
        Assert.assertEquals( BigDecimal.ZERO, orderBookHandler.getPriceToSellOrdersQtySumMap().values().stream().findFirst().get());

    }


    @Test
    public void testGetSummaryWithMultipleSellOrdersForDifferentUsers()  {
        ExampleData.buildExampleOrderBoardWithMultipleSellOrdersForDifferentUsers(orderBookHandler);
        List<String> expectedSummaryList = new ArrayList<>();
        expectedSummaryList.add("353.6 for £13.6");
        expectedSummaryList.add("441.8 for £13.9");
        expectedSummaryList.add("50.5 for £14");

        List<String> actualSummaryList =  orderBookHandler.getSummary(Side.SELL);
        //actualSummaryList.forEach(s -> System.out.println(s));

        Assert.assertEquals(expectedSummaryList, actualSummaryList);
    }

    @Test
    public void testGetSummaryWithMultipleBuyOrdersForDifferentUsers()  {
        ExampleData.buildExampleOrderBoardWithMultipleBuyOrdersForDifferentUsers(orderBookHandler);
        List<String> expectedSummaryList = new ArrayList<>();
        expectedSummaryList.add("50.5 for £14");
        expectedSummaryList.add("441.8 for £13.9");
        expectedSummaryList.add("353.6 for £13.6");

        List<String> actualSummaryList =  orderBookHandler.getSummary(Side.BUY);
        //actualSummaryList.forEach(s -> System.out.println(s));

        Assert.assertEquals(expectedSummaryList, actualSummaryList);
    }


}

