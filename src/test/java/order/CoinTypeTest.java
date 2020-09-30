package order;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoinTypeTest {

    @Test(expected = IllegalStateException.class)
    public void testFindByInputThrowsException() throws Exception {
        Side.findByInput("XXX");
    }

    @Test
    public void testFindByInput() throws Exception {
        assertEquals(CoinType.ETHEREUM, CoinType.findByInput("ETHEREUM"));
    }
}
