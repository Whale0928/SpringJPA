package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;


    /**아이템 객체 생성
     * Save.
     *
     * @param item the item
     */
    public void save(Item item){
        //아이템 아이디가 없으면 아예 새로 만드는 객체
        if(item.getId() == null) em.persist(item);
        //있는 경우에는 머지 수행
        else                     em.merge(item);
    }

    /**
     * 아이디로 아이템 탐색
     * Find one item.
     *
     * @param id the id
     * @return the item
     */
    public Item findOne(Long id){
        return em.find(Item.class,id);
    }



}
