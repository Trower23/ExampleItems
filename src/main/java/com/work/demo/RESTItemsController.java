package com.work.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class RESTItemsController {
    private Map<Integer, Items> compra = new ConcurrentHashMap<>();

    private AtomicInteger lastId = new AtomicInteger();

    @PostMapping("/items/")
    @ResponseStatus(HttpStatus.CREATED)
    public Items nuevoItem(@RequestBody Items item) {
        int id = lastId.incrementAndGet();
        item.setId(id);
        compra.put(id, item);
        return item;
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Items> getItems(@PathVariable int id){
        Items items = compra.get(id);
        if (items != null){
            return new ResponseEntity<>(items, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Items> deleteItems(@PathVariable int id){
        Items items= compra.get(id);
        compra.remove(id,items);
        if (items != null){
            return new ResponseEntity<>(items, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Items> putItems(@PathVariable int id, @RequestBody Items items){
        if (compra.containsKey(id)){
            items.setId(id);
            compra.put(id, items);
            return new ResponseEntity<>(items,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
