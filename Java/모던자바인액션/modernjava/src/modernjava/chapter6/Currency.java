package modernjava.chapter6;

public enum Currency {
    CHINA("위안화"), KOREAN("원화"), USA("달러");

    private final String name;

    Currency(final String name) {
        this.name = name;
    }
}
