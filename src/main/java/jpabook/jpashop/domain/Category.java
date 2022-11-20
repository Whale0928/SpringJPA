package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id@GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_itme",
        joinColumns =  @JoinColumn(name="category"),
            inverseJoinColumns = @JoinColumn(name="item_id")
    )//다대다를 풀어줘야하기 때문 컬렉션관계를 해결 실무 사용 금지
    private List<Item> items = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
