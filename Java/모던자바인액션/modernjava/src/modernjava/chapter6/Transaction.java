package modernjava.chapter6;

import modernjava.chapter5.Trader;

public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
    private final Currency currency;

    public Transaction(final Trader trader, final int year, final int value, final Currency currency) {

        this.trader = trader;
        this.year = year;
        this.value = value;
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    public Trader getTrader() {
        return trader;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
