package io.github.arkanjoms.store.resource;

import io.github.arkanjoms.store.model.Item;
import io.github.arkanjoms.store.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemResource {

    private final ItemRepository itemRepository;


    @GetMapping
    public ResponseEntity<Page<Item>> findAll(Pageable pageable) {
        return ResponseEntity.ok(itemRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item) {
        return ResponseEntity.ok(itemRepository.save(item));
    }
}
