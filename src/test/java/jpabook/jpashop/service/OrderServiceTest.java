package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    EntityManager em;

    /**
     * 회원정보 생성
     * Create item item.
     *
     * @return the item
     */
    private Item createItem() {
        Item book = new Book();
        book.setName("JAVA_Basic");
        book.setPrice(13000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    /**
     * 상품 정보 생성
     * Create member member.
     *
     * @return the member
     */
    private Member createMember() {
        Member member = new Member();
        member.setUsername("userA");
        member.setAddress(new Address("서울", "테헤란로", "111-111"));
        em.persist(member);
        return member;
    }

    /**
     * 상품 주문 성공 케이스
     * 상품주문.
     */
    @Test
    @DisplayName("상품주문 성공")
    public void 상품주문() {
        //given
        Member member = createMember();

        Item book = createItem();


        //when
        int orderCount = 3;
        Long order = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order doOrder = orderRepository.findOne(order);
        assertThat(doOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertEquals("주문시 상태는 ORDER", OrderStatus.ORDER, doOrder.getStatus());
        assertEquals("주문 개수가 동일해야 한다.", 1, doOrder.getOrderItems().size());
        assertEquals("주문 가격이 동일해야 한다 가격 * 개수", 13000 * 3, doOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 7, book.getStockQuantity());

    }


    /**
     * 재고수량 초과시 예외 처리 테스트
     */
    @Test(expected = NotEnoughStockException.class)
    @DisplayName("재고수량 초과시")
    public void 재고수량초과시() {
        //given
        Member member = createMember();
        Item item = createItem();
        //when
        orderService.order(member.getId(), item.getId(), 11);
        //then
        //여기전에 예외가 터져야 한다.
        fail();
    }

    /**
     * 주문취소.
     */
    @Test
    @DisplayName("주문 취소")
    public void 주문취소() {
        //given
        Member member = createMember();
        Item item = createItem();
        Long orderId = orderService.order(member.getId(), item.getId(), 5);
        //when
        orderService.cancelOrder(orderId);
        //then
        assertEquals("재고가 10개여야 함", 10, item.getStockQuantity());
    }


}