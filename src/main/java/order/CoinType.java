package order;

import java.util.Arrays;

public enum CoinType {
    LITECOIN("LITECOIN"),
    ETHEREUM("ETHEREUM");

    private String val;

    CoinType(String val) {
        this.val = val;
    }

    public static CoinType findByInput(String input) {
        return Arrays.stream(CoinType.values())
                .filter(e -> e.val.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", input)));
    }
}
