package com.woopaca.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clear();
    }

    @Test
    @DisplayName("아이템 저장")
    void itemSaveTest() throws Exception {
        //given
        Item item = Item.builder()
                .itemName("itemA")
                .itemPrice(10000)
                .quantity(10)
                .build();

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId()).orElse(null);
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    @DisplayName("아이템 목록")
    void findAllTest() throws Exception {
        //given
        Item item1 = Item.builder()
                .itemName("itemA")
                .itemPrice(10000)
                .quantity(10)
                .build();

        Item item2 = Item.builder()
                .itemName("itemB")
                .itemPrice(5000)
                .quantity(213)
                .build();

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    @DisplayName("아이템 수정")
    void updateItemTest() throws Exception {
        //given
        Item item = Item.builder()
                .itemName("itemA")
                .itemPrice(10000)
                .quantity(10)
                .build();

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateItem = Item.builder()
                .itemName("item 2")
                .itemPrice(5000)
                .quantity(9)
                .build();
        itemRepository.update(itemId, updateItem);

        //then
        Item findItem = itemRepository.findById(itemId).orElse(null);

        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}