package io.github.arkanjoms.store.repository;

import io.github.arkanjoms.store.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
