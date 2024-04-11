package org.example.itemservice.domain.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

  ItemRepository itemRepository = new ItemRepository();

  @AfterEach
  void afterEach() {
    itemRepository.clearStore();
  }

  @Test
  void save() {
    // given
    Item item = new Item("itemA", 1000, 10);

    // when
    Item savedItem = itemRepository.save(item);

    // then
    Item findItem = itemRepository.findById(item.getId());
    assertThat(savedItem).isEqualTo(findItem);
  }

  @Test
  void findAll() {
    // given
    Item item1 = new Item("itemA", 1000, 10);
    Item item2 = new Item("itemB", 2000, 20);

    itemRepository.save(item1);
    itemRepository.save(item2);

    // when
    List<Item> items = itemRepository.findAll();

    // then
    assertThat(items.size()).isEqualTo(2);
    assertThat(items).contains(item1, item2);
  }

  @Test
  void updateItem() {
    // given
    Item item = new Item("itemA", 1000, 10);
    itemRepository.save(item);

    // when
    Item updateParam = new Item("itemB", 2000, 20);
    itemRepository.update(item.getId(), updateParam);

    // then
    Item findItem = itemRepository.findById(item.getId());
    assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
    assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
  }

}