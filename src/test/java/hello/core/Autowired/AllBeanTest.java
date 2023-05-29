package hello.core.Autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);


        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPirce = discountService.discount(member, 10000,"fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPirce).isEqualTo(1000);


        int ratediscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(ratediscountPrice).isEqualTo(2000);

    }

    static class DiscountService{
        private final Map<String, DiscountPolicy> policy;
        private final List<DiscountPolicy> policies;

        @Autowired
        DiscountService(Map<String, DiscountPolicy> policy, List<DiscountPolicy> policies) {
            this.policy = policy;
            this.policies = policies;
            System.out.println("policy = " + policy);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policy.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
