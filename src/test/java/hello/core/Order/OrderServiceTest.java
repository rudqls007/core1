package hello.core.Order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createorder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);


    }




    /**
     * @Override
     *  public Order createOrder(Long memberId, String itemName, int itemPrice) {
     *  Member member = memberRepository.findById(memberId);
     *  int discountPrice = discountPolicy.discount(member, itemPrice);
     *  return new Order(memberId, itemName, itemPrice, discountPrice);
     * */
}
