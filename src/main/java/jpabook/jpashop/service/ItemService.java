package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repo;


    /**
     * Save item.
     * @param item the item
     */
    @Transactional
    public void saveItem(Item item){
        repo.save(item);
    }

    /**
     * Find items list.
     * @return the list
     */
    public List<Item> findItems(){
        return findItems();
    }



}
