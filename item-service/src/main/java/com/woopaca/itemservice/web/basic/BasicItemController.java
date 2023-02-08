package com.woopaca.itemservice.web.basic;

import com.woopaca.itemservice.domain.item.Item;
import com.woopaca.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        itemRepository.save(Item.builder()
                .itemName("itemA")
                .price(10000)
                .quantity(50)
                .build());
        itemRepository.save(Item.builder()
                .itemName("itemB")
                .price(39000)
                .quantity(346)
                .build());
    }

    @GetMapping("")
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new IllegalArgumentException("회원 없음"));
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    // @ModelAttribute는 Model 객체를 만들고, "item"이라는 이름으로 Model에 객체를 담는다.
    // ("item")은 클래스 이름의 맨 앞 글자만 소문자로 바꾼 것과 같기 때문에 생략 가능하다.
    // @ModelAttribute도 생략 가능하다.
    @PostMapping("/add")
    public void save(@ModelAttribute("item") Item item, HttpServletResponse response)
            throws IOException {
        Item savedItem = itemRepository.save(item);
        response.sendRedirect("/basic/items/" + savedItem.getId());
    }
}
