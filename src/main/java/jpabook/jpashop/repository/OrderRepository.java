package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    /**
     * 단건 조회
     * Find one order.
     *
     * @param id the id
     * @return the order
     */
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    //검색
    public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m";

        boolean isFirstCondition = true;

        //주문 상태 검색 조건
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += "o.status = :status";
        }

        //회원 이름 검색 조건
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " where";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class).setMaxResults(1000);

        if (orderSearch.getOrderStatus() != null) query = query.setParameter("status", orderSearch.getOrderStatus());
        if (StringUtils.hasText(orderSearch.getMemberName()))
            query = query.setParameter("name", orderSearch.getMemberName());

        return em.createQuery(jpql, Order.class)
                .setMaxResults(1000)//최대 1000건
                .getResultList();// ;
    }

}
