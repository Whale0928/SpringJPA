package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private Member member;
}
