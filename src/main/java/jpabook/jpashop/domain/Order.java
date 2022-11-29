package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    @ManyToOne(fetch = LAZY) //회원과 다대일 관계  여러개의 주문 : 하나의 회원 정보
    @JoinColumn(name = "member_id")
    // 주문과 회원 도메인 어느쪽이 기준(주인)인지 정리해야 한다 ( 기준의 값이 변했으면 다른 값을 변경 )
    private Member member;

    //주문 아이템들
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    //배송 정보
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //주문 시간
    //자바8이상에서는 로컬 날짜를 쓰면 하이버네이트가 자동으로 매핑해준다.
    private LocalDateTime orderDate;

    //주문 상태 [ ORDER,CANCEL ]
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //연관관계 메서드

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }


    /**
     * 생성 메서드 -> 복잡한 생성 별도의 생성 전용 메서드가 있는 것이 좋다
     * Creat order order.
     *
     * @param member     the member
     * @param delivery   the delivery
     * @param orderItems the order items
     * @return the order
     */
    public static Order creatOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem item : orderItems) {
            order.addOrderItem(item);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }


}
