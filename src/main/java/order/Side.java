package order;

import java.util.Arrays;

public enum Side {
    BUY("BUY"),
    SELL("SELL");

    private String val;

    Side(String val) {
        this.val = val;
    }

    public static Side findByInput(String input) {
        return Arrays.stream(Side.values())
                .filter(e -> e.val.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", input)));
    }
}
