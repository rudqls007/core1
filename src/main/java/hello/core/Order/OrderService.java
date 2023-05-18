package hello.core.Order;

public interface OrderService {
    Order createorder(Long memberId, String itemName, int itemPrice);
}
