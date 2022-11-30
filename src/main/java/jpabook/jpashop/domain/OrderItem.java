package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * The type Order item.
 */
@Entity
@Getter@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name="order_item_id")
    private Long id;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    //주문 가격
    private int orderPrice;
    //주문 수량
    private int count;

    /**생성자 제어
     * Instantiates a new Order item.
     */
    protected OrderItem() {
    }

    /**생성 메서드 => 주문 아이템 생성
     * Create order item.
     *
     * @param item       the item
     * @param orderPrice the order price
     * @param count      the count
     * @return the order item
     */
    public static OrderItem createOrderItem(Item item,int orderPrice ,int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        //구매한 제품만큼 재고를 제거
        item.removeStock(count);
        return orderItem;
    }
    /**주문 취소 [ 비지니스 로직 ]
     * Cancel.
     */
    public void cancel() {
        getItem().addStock(count); // 저장되어 있던 재고 수량을 다시 추가해 원상 복구 한다.
    }

    /**가격 조회 로직 [ 조회 로직 ]
     * Get total price int.
     *
     * @return the int
     */
    public int getTotalPrice(){return getOrderPrice()* getCount();}
}
