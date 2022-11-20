package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    @ManyToOne //회원과 다대일 관계  여러개의 주문 : 하나의 회원 정보
    @JoinColumn(name="member_id")
    // 주문과 회원 도메인 어느쪽이 기준(주인)인지 정리해야 한다 ( 기준의 값이 변했으면 다른 값을 변경 )
    private Member member;

    //주문 아이템들
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    //배송 정보
    private Delivery delivery;

    //주문 시간
    //자바8이상에서는 로컬 날짜를 쓰면 하이버네이트가 자동으로 매핑해준다.
    private LocalDateTime orderDate;

    //주문 상태 [ ORDER,CANCEL ]
    private OrderStatus status;
}
