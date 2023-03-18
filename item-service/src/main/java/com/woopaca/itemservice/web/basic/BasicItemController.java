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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
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
    /*@PostMapping("/add")
    public String save(@ModelAttribute("item") Item item) {
        Item savedItem = itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }*/

    @PostMapping("/add")
    public String save(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId()); // redirect path variable로 사용
        redirectAttributes.addAttribute("status", true); // redirect 페이지에서 사용
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new IllegalArgumentException("회원 없음"));
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String update(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}"; // PathVariable의 값을 사용 가능
    }
}
