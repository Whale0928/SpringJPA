package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired MemberService memberService;
    @Autowired ItemService itemService;
    @Autowired OrderRepository orderRepository;
    @Autowired EntityManager em;

    @Test
    @DisplayName("상품주문 성공")
    public void 상품주문() {
        //given
        Member member = new Member();
        member.setUsername("userA");
        member.setAddress(new Address("서울","테헤란로","111-111"));
        em.persist(member);

        Item book = new Book();
        book.setName("JAVA_Basic");
        book.setPrice(13000);
        book.setStockQuantity(10);
        em.persist(book);


        //when
        int orderCount = 3;
        Long order = orderService.order(member.getId(), book.getId(),  orderCount);

        //then
        Order doOrder = orderRepository.findOne(order);
        assertThat(doOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertEquals("주문시 상태는 ORDER",OrderStatus.ORDER,doOrder.getStatus());
        assertEquals("주문 개수가 동일해야 한다.",1,doOrder.getOrderItems().size());
        assertEquals("주문 가격이 동일해야 한다 가격 * 개수",13000*3,doOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.",7,book.getStockQuantity());

    }

    @Test
    @DisplayName("주문 취소")
    public void 주문취소() {
        //given
        //when
        //then
    }

    @Test
    @DisplayName("재고수량 초과시")
    public void 재고수량초과시() {
        //given
        //when
        //then
    }
}