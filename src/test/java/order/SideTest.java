package order;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SideTest {

    @Test(expected = IllegalStateException.class)
    public void testFindByInputThrowsException() throws Exception {
        Side.findByInput("XXX");
    }

    @Test
    public void testFindByInput() throws Exception {
        assertEquals(Side.BUY, Side.findByInput("BUY"));
    }
}
