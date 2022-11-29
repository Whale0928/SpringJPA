package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //싱글 테이블 전략
@DiscriminatorColumn(name="dtype")
@Getter@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    // 구현 비지니스 로직


    /** 재고 증가
     * Add stock.
     * @param quantity the quantity
     */
    public void addStock(int quantity){
        this.stockQuantity+=quantity; //재고 수량 증가
    }

    /**재고 감소
     * Remove stock.
     * @param quantity the quantity
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity-quantity; //재고 수량 증가

        if(restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
