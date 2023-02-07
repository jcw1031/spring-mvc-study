package com.woopaca.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class ItemRepository {

    // 스프링 컨테이너 안에서 사용하면 싱글톤이기 때문에 static으로 선언하지 않아도 되지만, new 키워드로 직접 생성하여 사용하는 경우를 고려하여 static 선언
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        return store.put(item.getId(), item);
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Item> findAll() {
        // 바로 store.values()를 반환하면 store가 외부에 노출되어 값이 변경될 위험성이 있다.
        return new ArrayList<>(store.values());
    }

    public void update(Long id, Item updateParam) {
        Item findItem = findById(id).orElseThrow(NoSuchElementException::new);
        findItem.setItemName(updateParam.getItemName());
        findItem.setItemPrice(updateParam.getItemPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clear() {
        store.clear();
    }
}