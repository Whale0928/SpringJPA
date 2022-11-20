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

    //주문 정보들과 일대다 관계
    //여기는 읽기 전용이 된다.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
