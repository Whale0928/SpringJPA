package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    /**단건 조회
     * Find one order.
     *
     * @param id the id
     * @return the order
     */
    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    //검색
    //public List<Order> findAll(OrderSearch orderSearch){}

}
