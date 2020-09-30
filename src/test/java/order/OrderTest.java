package order;

import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.assertTrue;

public class OrderTest {

    @Test
    public void testOrderEqualityBasedOnOrderIdOnly() throws Exception {
        Order order1 = new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125"));
        Order order2 = new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125"));
        assertTrue(order1.equals(order2));
    }

    @Test
    public void testOrderEqualityBasedOnOrderIdOnlyWithDifferentPrice() throws Exception {
        Order order1 = new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("125"));
        Order order2 = new Order(1l, "userId1", CoinType.LITECOIN, Side.BUY,new BigDecimal("1"), new BigDecimal("126"));
        assertTrue(order1.equals(order2));
    }
}
