package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue//시퀀스
    @Column(name="member_id")
    private Long id;

    private String username;

    @Embedded
    private Address address;

    @OneToMany //주문 정보들과 일대다 관계
    private List<Order> orders = new ArrayList<>();
}
