package chapter1.item1;

public class Order {
    private boolean prime;
    private Product product;
    private boolean urgent;




    // 정적 팩토리 메서드
    public static Order primeOrder(Product product) {
        Order order = new Order();
        order.prime = true;
        order.product = product;
        return order;
    }

    public static Order urgentOrder(Product product) {
        Order order = new Order();
        order.urgent = true;
        order.product = product;
        return order;
    }


}
