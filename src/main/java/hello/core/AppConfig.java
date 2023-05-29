package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService --> new MemoryMemberRepository()
    // @Bean orderService -->  new MemoryMemberRepository()

    @Bean
    public MemberService memberService(){
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService OrderService() {
        System.out.println("Call AppConfig.OrderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return  null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return  new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
