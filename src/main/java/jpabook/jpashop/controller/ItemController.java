package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    /**
     * 아이템 등록 폼 페이지로 이동
     * Create form string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    /**
     * 아이템 폼을 등록
     * Create string.
     *
     * @param bookForm the book form
     * @return the string
     */
    @PostMapping("/items/new")
    public String create(BookForm bookForm) {
        Book book = bookSettingMethod(bookForm);
        itemService.saveItem(book);
        return "redirect:list";
    }

    /**
     * 저장된 아이템 목록들을 조회
     * Item list string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/items/list")
    public String ItemList(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    /**
     * 등록된 아이템 리스트를 수정
     * Update item form string.
     *
     * @param itemId the item id
     * @param model  the model
     * @return the string
     */
    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Book item = (Book) itemService.findById(itemId);
        //업데이트 할 떄 ! 북 엔티티 자체를 보내는 것이 아닌 , 폼 객체를 전달한다.
        BookForm bookForm = new BookForm();
        bookForm.setId(item.getId());
        bookForm.setName(item.getName());
        bookForm.setAuthor(item.getAuthor());
        bookForm.setStockQuantity(item.getStockQuantity());
        bookForm.setIsbn(item.getIsbn());
        bookForm.setPrice(item.getPrice());
        model.addAttribute("form", bookForm);
        return "items/updateItemForm";
    }

    /**아이템 수정
     * Update item string.
     *
     * @param bookForm the book form
     * @param itemId   the item id
     * @return the string
     */
    @PostMapping("items/{itemId}/edit")
    public String updateItem(BookForm bookForm, @PathVariable String itemId) {
        Book book = bookSettingMethod(bookForm);
        itemService.saveItem(book);
        return "redirect:/items/list";
    }

    /** book form 세팅 메서드
     * Book setting method book.
     *
     * @param bookForm the book form
     * @return the book
     */
    private static Book bookSettingMethod(BookForm bookForm) {
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setIsbn(bookForm.getIsbn());
        book.setAuthor(bookForm.getAuthor());
        return book;
    }


}
