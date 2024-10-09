package modernjava.chapter6;

import modernjava.chapter5.Trader;

import java.util.*;
import java.util.stream.Collectors;

public class GroupEx {
    public static void main(String[] args) {


        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300, Currency.KOREAN),
                new Transaction(raoul, 2012, 1000, Currency.USA),
                new Transaction(raoul, 2011, 400, Currency.USA),
                new Transaction(mario, 2012, 710, Currency.CHINA),
                new Transaction(mario, 2012, 700, Currency.KOREAN),
                new Transaction(alan, 2012, 950, Currency.USA)

        );
        Map<Currency, List<Transaction>> transactionsByCurrencies
                = new HashMap<>();

        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> list = transactionsByCurrencies.get(currency);
            if (list == null) {
                list = new ArrayList<>();
                transactionsByCurrencies.put(currency, list);
            }
            list.add(transaction);
        }


        Map<Currency, List<Transaction>> collect = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
    }
}
