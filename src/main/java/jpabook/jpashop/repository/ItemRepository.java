package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;


    /**
     * 아이템 객체 생성
     * Save.
     *
     * @param item the item
     */
    public void save(Item item) {
        //아이템 아이디가 없으면 아예 새로 만드는 객체
        //있는 경우에는 머지 수행
        if (item.getId() == null) em.persist(item);
        else em.merge(item);
    }

    /**
     * 아이디로 아이템 탐색
     * Find one item.
     *
     * @param id the id
     * @return the item
     */
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }


    /**
     * 아이템 목록 조회
     * Find all list.
     *
     * @return the list
     */
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }


}
