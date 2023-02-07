package com.woopaca.itemservice.web.basic;

import com.woopaca.itemservice.domain.item.Item;
import com.woopaca.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
                .itemPrice(10000)
                .quantity(50)
                .build());
        itemRepository.save(Item.builder()
                .itemName("itemB")
                .itemPrice(39000)
                .quantity(346)
                .build());
    }

    @GetMapping("")
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }
}
