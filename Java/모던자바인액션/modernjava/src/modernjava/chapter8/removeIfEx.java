package modernjava.chapter8;

import java.util.Iterator;
import java.util.List;

public class removeIfEx {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction(),
                new Transaction(),
                new Transaction()
        );

        for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();) {
            Transaction transaction= iterator.next();

            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                transactions.remove(transaction);
            }


        }

    }
    static class Transaction{
        String ReferenceCode;

        public String getReferenceCode() {
            return ReferenceCode;
        }
    }
}
